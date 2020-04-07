package my.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityExistsException;

@ControllerAdvice
public class ExceptionHandler {

    public ResponseEntity<Object> handleExistsException(EntityExistsException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
