package fr.eni.demospringwebservices.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity @Table(name = "cocktails")
public class Cocktail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "asso_cocktail_ingredient",
            joinColumns = @JoinColumn(name = "cocktail_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    private float prix;

    private boolean alcoolise;
}
