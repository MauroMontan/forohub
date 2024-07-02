package mauro.montan.forohub.errorHandlers;

import jakarta.servlet.http.HttpServletRequest;

import mauro.montan.forohub.users.UserAlreadyExistException;
import mauro.montan.forohub.users.errorHandlers.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler {

    private final HttpServletRequest request;
    Logger logger = LoggerFactory.getLogger(RestControllerAdvice.class);

    public RestExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }



    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExist() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMismatch() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleResourceNotFound() {
        String ipAddress = request.getRemoteAddr();
        logger.warn("A non existing resource has been requested by [" + ipAddress + "] ");
        return ResponseEntity.notFound().build();
    }
}

