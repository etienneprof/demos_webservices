package fr.eni.demospringwebservices.handlers;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Locale;

@ControllerAdvice
public class ExceptionHandler { // ma classe porte le meme nom que l'annotation utilisée... pas bien
    private final MessageSource messageSource; // permet de charger les messages du messages.properties

    public ExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> capturerException(MethodArgumentNotValidException manve, Locale locale) {
        StringBuilder message = new StringBuilder();

        message.append(messageSource.getMessage("exception.sportif.title", null, locale));
        message.append("\n\t");

        for (var error : manve.getAllErrors()) {
            message.append(error.getDefaultMessage());
            message.append("\n\t");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.toString());
    }


}
