package macrobrang.dpvat.Exception;

import jakarta.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "ERROR_GLOBAL_EXCEPTION") 
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //Imprime nos logs os erros de exeção. Configurado por padrão como false, application.properties 
    //NÃO DEVE SER TRUE EM PRODUÇÃO
    @Value("${server.error.include-exception}")
    private boolean printStackTrace;

    /**
     *  Este método lida com exceções relacionadas à validação de dados usando anotações como @Valid, @NotBlank, @Size, etc.
     *  Se alguma validação falhar, este método será chamado para lidar com a exceção.
     * 
     * @param ex A exceção MethodArgumentNotValidException que será interceptada.
     * @param headers Os cabeçalhos da requisição que acionou a exceção.
     * @param status status O status HTTP será 422 (UNPROCESSABLE_ENTITY), indicando uma falha de validação.
     * @param request A WebRequest que intercepta toda a requisição, incluindo informações como status e cabeçalhos.
     * 
     * @return Um ResponseEntity contendo informações de erro específicas para a validação, incluindo detalhes sobre os campos de erro.
     */
    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, 
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {

        var responseError = new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(),
        "Erro de Validação, por favor verifique os campos de erros para mais detalhes");

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            responseError.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(responseError);
    }

    /**
     * Este método lida com exceções relacionadas a conflitos de dados, como violações de integridade. 
     * Ele é acionado quando uma requisição quebra a integridade dos dados no sistema.
     * 
     * @param dataIntegrityViolationException A exceção dataIntegrityViolationException que será interceptada
     * @param request A WebRequest que intercepta toda a requisição, incluindo informações como status e cabeçalhos. 
     * @return @return Um ResponseEntity contendo informações de erro específicas para a violação de integridade.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT) //HttpStatus 409
    public ResponseEntity<Object> handleDataIntegrityViolationException(
        DataIntegrityViolationException dataIntegrityViolationException,
        WebRequest request) {

            final String message = dataIntegrityViolationException.getMostSpecificCause().getMessage();
            log.error("Erro de integridade ao tentar salvar os dados: " + message, dataIntegrityViolationException);
            
            return buildResponseError(
                dataIntegrityViolationException, 
                message, 
                HttpStatus.CONFLICT, 
                request
            );
    }

    /**
     * Este método lida com exceções que não foram tratadas na aplicação, como exceções genéricas não tratadas.
     * @param ex A classe de erro genérica Exception que não foi tratada.
     * @param request A WebRequest que intercepta toda a requisição, incluindo informações como status e cabeçalhos. 
     * @return Um ResponseEntity contendo informações de erro relacionadas à exceção não tratada.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //HttpStatus 500
    public ResponseEntity<Object> handleUnknownErrorException(
        Exception ex,
        WebRequest request) {

            final String errorMessage = "Erro desconhecido ocorreu";
            
            log.error(errorMessage, ex);
            
            return buildResponseError(
                ex,
                errorMessage,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
            );
    }

    /**
     * Lida com exceções de violação de restrições (ConstraintViolationException) que ocorrem durante a validação.
     * 
     * @param constraintViolationExceptionconstraintViolationException A exceção ConstraintViolationException que será tratada.
     * @param request A WebRequest que intercepta a requisição, fornecendo informações adicionais.
     * @return Um ResponseEntity contendo informações de erro relacionadas à violação de restrições.
     */
    public ResponseEntity<Object> handleConstraintViolationException(
        ConstraintViolationException constraintViolationException,
        WebRequest request) {

            log.error("Falha ao validar: ", constraintViolationException);
            return buildResponseError(
                constraintViolationException, 
                HttpStatus.UNPROCESSABLE_ENTITY, 
                request
            );
    }

    /**
     * Constrói uma resposta de erro com base nos parâmetros fornecidos.
     * @param ex A exceção que desencadeou o erro.
     * @param message A mensagem de erro específica.
     * @param httpStatus O status HTTP a ser retornado na resposta.
     * @param request A WebRequest que intercepta a requisição.
     * @return Um ResponseEntity contendo informações de erro.
     */
    private ResponseEntity<Object> buildResponseError(
        Exception ex,
        String message,
        HttpStatus httpStatus,
        WebRequest request) {
        
            var responseError = new ResponseError(httpStatus.value(), message);

            if(this.printStackTrace) {
            responseError.setStackTrace(ExceptionUtils.getStackTrace(ex));
            }

            return ResponseEntity.status(httpStatus).body(responseError);
    }

    /**
     * Constrói uma resposta de erro padronizada com base em uma exceção, status HTTP e informações da solicitação.
     * 
     * @param ex A exceção que desencadeou o erro.
     * @param status O status HTTP a ser retornado na resposta.
     * @param request A WebRequest que intercepta a requisição e fornece informações adicionais.
     * @return Um ResponseEntity contendo informações de erro.
     */
    private ResponseEntity<Object> buildResponseError(
        Exception ex,
        HttpStatus status,
        WebRequest request
    ) {
        return buildResponseError(ex, ex.getMessage(), status, request);
    }
}
