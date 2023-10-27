package siqueir4.dpvat.ServiceImplements.Exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DeletionNotAllowedException extends DataIntegrityViolationException {

    public DeletionNotAllowedException(String message) {
        super(message);
    }

}
