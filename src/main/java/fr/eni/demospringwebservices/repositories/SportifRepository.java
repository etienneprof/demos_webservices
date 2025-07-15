package fr.eni.demospringwebservices.repositories;

import fr.eni.demospringwebservices.entities.Sportif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportifRepository extends JpaRepository<Sportif, Integer> {
}
