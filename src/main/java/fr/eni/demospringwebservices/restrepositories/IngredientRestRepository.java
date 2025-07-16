package fr.eni.demospringwebservices.restrepositories;

import fr.eni.demospringwebservices.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface IngredientRestRepository extends JpaRepository<Ingredient,Integer> {
}
