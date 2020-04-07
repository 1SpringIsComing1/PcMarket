package my.market.exception;

import my.market.model.response.CustomErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityExistsException;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandler {

    public ResponseEntity<Object> handleExistsException(EntityExistsException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // custom message
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(ex.getMessage(), new Date());
        return new ResponseEntity<>(customErrorMessage, HttpStatus.BAD_REQUEST);
    }
}
