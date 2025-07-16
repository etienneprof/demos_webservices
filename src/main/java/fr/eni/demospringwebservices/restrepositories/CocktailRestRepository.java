package fr.eni.demospringwebservices.restrepositories;

import fr.eni.demospringwebservices.entities.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CocktailRestRepository extends JpaRepository<Cocktail, Integer> {
}
