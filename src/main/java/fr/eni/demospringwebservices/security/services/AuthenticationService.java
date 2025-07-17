package fr.eni.demospringwebservices.security.services;

import fr.eni.demospringwebservices.security.JwtService;
import fr.eni.demospringwebservices.security.controllers.AuthenticationRequest;
import fr.eni.demospringwebservices.security.controllers.AuthenticationResponse;
import fr.eni.demospringwebservices.security.entities.User;
import fr.eni.demospringwebservices.security.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        User user = userRepository.findByLogin(request.getLogin());

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
