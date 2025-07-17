package fr.eni.demospringwebservices.security.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class AuthenticationResponse {
    private String token;
}
