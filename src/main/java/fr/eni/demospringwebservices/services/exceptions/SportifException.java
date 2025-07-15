package fr.eni.demospringwebservices.services.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SportifException extends Exception {
    private final List<String> _errors = new ArrayList<>();

    public void addError(String error) {
        _errors.add(error);
    }

    public boolean isPristine() {
        return _errors.isEmpty();
    }
}
