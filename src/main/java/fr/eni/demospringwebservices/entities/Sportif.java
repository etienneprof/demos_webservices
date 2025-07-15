package fr.eni.demospringwebservices.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity @Table(name = "sportifs")
public class Sportif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    private LocalDate date;

    private LocalDate derniereLicence;

    private boolean actif;

    private String mail;

    private String telephone;

}
