package fr.eni.demospringwebservices.controllers;

import fr.eni.demospringwebservices.entities.Sportif;
import fr.eni.demospringwebservices.services.SportifService;
import fr.eni.demospringwebservices.services.exceptions.SportifException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sportifs")
public class SportifController {

    private final SportifService sportifService;

    public SportifController(SportifService sportifService) {
        this.sportifService = sportifService;

        Sportif sportif = Sportif.builder()
                .nom("Teddy RINNER")
                .date(LocalDate.of(1980, 5, 5))
                .mail("rinner@gmail.com")
                .derniereLicence(LocalDate.of(2024, 9, 1))
                .telephone("0600000000")
                .actif(true)
                .build();
        try {
            sportifService.save(sportif);
        } catch (SportifException e) {
            throw new RuntimeException();
        }
    }

    @GetMapping
    public ResponseEntity<List<Sportif>> findAll() {
        var list = sportifService.findAll();
        if  (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Sportif sportif) {
        if (sportif.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Sportif newSportif = null;
        try {
            newSportif = sportifService.save(sportif);
        } catch (SportifException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.get_errors());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newSportif);
    }
}
