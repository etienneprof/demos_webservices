package fr.eni.demospringwebservices.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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

    @Size(min = 4, max = 80)
    @Column(length = 80)
    private String nom;

    @Past
    private LocalDate date;

    @Past
    private LocalDate derniereLicence;

    private boolean actif;

    @Email
    private String mail;

    private String telephone;

}
