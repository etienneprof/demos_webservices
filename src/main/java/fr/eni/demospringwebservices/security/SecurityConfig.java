package fr.eni.demospringwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
                    .requestMatchers("/auth").permitAll()

                    // Les employés et les administrateurs ont le droit de consulter les sportifs
                    .requestMatchers(HttpMethod.GET, "/sportifs").hasAnyRole("EMPLOYE", "ADMIN")

                    // Seuls les administrateurs ont le droit d'altérer les données des sportifs
                    .requestMatchers(HttpMethod.POST, "/sportifs").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/sportifs").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/sportifs").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/sportifs").hasRole("ADMIN")

                    .anyRequest().denyAll();
        });
        // Utilisation d'un systeme de connexion configuré pour JWT
        http.authenticationProvider(authenticationProvider);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // http.csrf(csrf -> csrf.disable());
        http.csrf(AbstractHttpConfigurer::disable); // /!\ JAMAIS DE LA VIE EN PRODUCTION

        return http.build();
    }
}
