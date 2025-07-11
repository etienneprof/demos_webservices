package fr.eni.demospringwebservices.repositories;

import fr.eni.demospringwebservices.entities.Crayon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrayonRepository extends JpaRepository<Crayon,Integer> {
}
