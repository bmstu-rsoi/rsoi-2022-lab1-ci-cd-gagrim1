package javatest.ru.romanov.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class PersonEndpointHandler {
    public static String msgEntityNotFound = "ERROR: личность не найдена.";
    public static String msgWrongId = "ERROR: личность с id не найдена.";
    public static String msgWrongArgument = "ERROR: неверные входные данные.";


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        Error err = new Error()
                .setMessage(HttpStatus.NOT_FOUND.toString())
                .setDescription(msgEntityNotFound);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        Error err = new Error()
                .setMessage(HttpStatus.BAD_REQUEST.toString())
                .setDescription(msgWrongId);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Error err = new Error()
                .setMessage(HttpStatus.BAD_REQUEST.toString())
                .setDescription(msgWrongArgument);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(err);
    }
}
