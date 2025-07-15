package fr.eni.demospringwebservices.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    @Size(min = 4, max = 80, message = "{exception.sportif.nom}")
    @Column(length = 80)
    private String nom;

    @Past(message = "{exception.sportif.date}")
    private LocalDate date;

    @Past(message = "{exception.sportif.derniereLicence}")
    private LocalDate derniereLicence;

    private boolean actif;

    @Email(message = "{exception.sportif.mail}")
    private String mail;

    private String telephone;

}
