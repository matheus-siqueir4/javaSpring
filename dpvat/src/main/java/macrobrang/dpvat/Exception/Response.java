package macrobrang.dpvat.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.Data;

/**
 * Essa classe irá gerar os erros de respostas.
 * 
 */

@Data
public class Response<T> {
    
    private T obj;
    private Map<String, String> mapErrors = new HashMap<>();
    
    public Map<String, String> addValidationError(BindingResult result) {
        
        result.getAllErrors().forEach((error) -> {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                mapErrors.put( fieldName, errorMessage);
            }
        });

        return mapErrors;
    }

}
