package fr.eni.demospringwebservices.services;

import fr.eni.demospringwebservices.entities.Sportif;
import fr.eni.demospringwebservices.repositories.SportifRepository;
import fr.eni.demospringwebservices.services.exceptions.SportifException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SportifService {

    private final SportifRepository sportifRepository;

    public SportifService(SportifRepository sportifRepository) {
        this.sportifRepository = sportifRepository;
    }

    public List<Sportif> findAll() {
        return sportifRepository.findAll();
    }

    public Sportif save(Sportif sportif) throws SportifException {
        // checkSportif(sportif);

        return sportifRepository.save(sportif);
    }

    private void checkSportif(Sportif sportif) throws SportifException {
        SportifException se = new SportifException();

        if (sportif.getDate().isAfter(LocalDate.now()))
            se.addError("Le sportif doit être né !");

        if (sportif.getDerniereLicence().isAfter(LocalDate.now()))
            se.addError("Impossible d'avoir une licence dans le futur !");

        if (sportif.getDerniereLicence().isBefore(sportif.getDate()))
            se.addError("Impossible d'obtenir une licence avant d'être né !");

        if (sportif.getNom().length() < 4)
            se.addError("Le nom doit faire au moins 4 caractères");

        if (sportif.getNom().length() > 80)
            se.addError("Le nom ne doit pas excéder 80 caractères");

        if (!sportif.getMail().matches("^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$"))
            se.addError("L'email n'est pas valide !");

        if (!se.isPristine()) {
            throw se;
        }
    }
}
