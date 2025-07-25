package fr.eni.demospringwebservices.security.repositories;

import fr.eni.demospringwebservices.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByLogin(String login);
}
