package ru.borisov.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(TicketException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public TicketResponse handleTicketException(TicketException ex) {
        return new TicketResponse(ex.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public InvalidPasswordResponse handleInvalidPasswordException(InvalidPasswordException ex) {
        return new InvalidPasswordResponse(ex.getMessage());
    }


}
