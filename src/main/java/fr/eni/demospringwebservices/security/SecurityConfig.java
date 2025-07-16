package fr.eni.demospringwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // J'indique à Spring qu'il doit utiliser cette méthode si il a besoin d'un UserDetailsManager
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select login, password, 1 from users where login = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select login, authority from users where login = ?");
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
                    // Autorise l'accès à l'url /eniecole pour tout le monde
                    .requestMatchers("/eniecole").permitAll()

                    // Les employés et les administrateurs ont le droit de consulter les sportifs
                    .requestMatchers(HttpMethod.GET, "/sportifs").hasAnyRole("EMPLOYE", "ADMIN")

                    // Seuls les administrateurs ont le droit d'altérer les données des sportifs
                    .requestMatchers(HttpMethod.POST, "/sportifs").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/sportifs").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/sportifs").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/sportifs").hasRole("ADMIN")

                    .anyRequest().denyAll();
        });
        // Utilisation d'un systeme de connexion "par défaut" avec un identifiant et un mot de passe
        http.httpBasic(Customizer.withDefaults());

        // http.csrf(csrf -> csrf.disable());
        http.csrf(AbstractHttpConfigurer::disable); // /!\ JAMAIS DE LA VIE EN PRODUCTION

        return http.build();
    }
}
