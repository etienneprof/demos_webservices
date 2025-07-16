package fr.eni.demospringwebservices.restrepositories;

import fr.eni.demospringwebservices.entities.Cocktail;
import fr.eni.demospringwebservices.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cocktails", path = "cocktails")
public interface CocktailRestRepository extends JpaRepository<Cocktail, Integer> {
    List<Cocktail> findByNomContains(String nom);
    List<Cocktail> findByAlcooliseIs(Boolean alcoolise);
}
