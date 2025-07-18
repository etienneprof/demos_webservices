package fr.eni.demospringwebservices.services;

import fr.eni.demospringwebservices.entities.Sportif;
import fr.eni.demospringwebservices.repositories.SportifRepository;
import fr.eni.demospringwebservices.services.exceptions.SportifException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SportifServiceTest {
    private SportifService sportifService;

    @Mock
    private SportifRepository sportifRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sportifService = new SportifService(sportifRepository);
    }

    @Test
    void save_validParameters_insertSportif() {
        Sportif sportif = Sportif.builder()
                .nom("Noah Yannick")
                .date(LocalDate.of(1960, 5, 6))
                .derniereLicence(LocalDate.of(1990, 8, 9))
                .actif(false)
                .mail("yannick@gmail.com")
                .telephone("02XXXXXXXX")
                .build();

        when(sportifRepository.save(sportif)).then(answer -> {
            sportif.setId(1);
            return sportif;
        });

        try {
            Sportif created = sportifService.save(sportif);
            assertTrue(created.getId() != null && created.getId() > 0);
        } catch (SportifException e) {
            fail("Les données fournies n'ont pas permis de créer un sportif. Message : " + e.getMessage());
        }
    }

    @Test
    void save_invalidParametersWithShortName_throwsSportifException() {
        Sportif sportif = Sportif.builder()
                .nom("Noa")
                .date(LocalDate.of(2060, 5, 6))
                .derniereLicence(LocalDate.of(2050, 8, 9))
                .actif(false)
                .mail("pouet")
                .telephone("02XXXXXXXX")
                .build();

        when(sportifRepository.save(sportif)).then(answer -> {
            sportif.setId(1);
            return sportif;
        });

        SportifException se = assertThrows(SportifException.class, () -> sportifService.save(sportif));
        /*
         * Plusieurs asserts sur les messages d'erreurs
         */
    }

    @Test
    void save_invalidParametersWithLongName_throwsSportifException() {
        Sportif sportif = Sportif.builder()
                .nom("NoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoaNoa")
                .date(LocalDate.now())
                .derniereLicence(LocalDate.now())
                .actif(false)
                .mail("pouet")
                .telephone("02XXXXXXXX")
                .build();

        when(sportifRepository.save(sportif)).then(answer -> {
            sportif.setId(1);
            return sportif;
        });

        SportifException se = assertThrows(SportifException.class, () -> sportifService.save(sportif));
        /*
         * Plusieurs asserts sur les messages d'erreurs
         */
    }
}