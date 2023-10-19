package macrobrang.dpvat.Exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class ResponseError {
    
    private final int status;
    private final String message;
    private String stackTrace;
    private List<ValidationError> errors;

    @Data
    public static class ValidationError {
        
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message) {

        if (Objects.isNull(errors)) {
            this.errors = new ArrayList<>();
        }

        this.errors.add(new ValidationError(field, message));
    }
}
