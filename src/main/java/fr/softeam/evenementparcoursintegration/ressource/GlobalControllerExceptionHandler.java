package fr.softeam.evenementparcoursintegration.ressource;

import fr.softeam.evenementparcoursintegration.exception.EvenementParcoursIntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(EvenementParcoursIntegrationException.class)
    public String handleGestionEvenementException(EvenementParcoursIntegrationException exc){
        return exc.getMessage();
    }
}
