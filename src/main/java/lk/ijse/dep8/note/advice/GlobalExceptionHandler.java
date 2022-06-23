package lk.ijse.dep8.note.advice;

import lk.ijse.dep8.note.service.exception.DuplicateEmailException;
import lk.ijse.dep8.note.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateEmailException.class)
    public String handleDuplicateEmailException(DuplicateEmailException e){
        e.initCause(new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage()));
        return e.getMessage();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException e){
        e.initCause(new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage()));
        return e.getMessage();
    }
}
