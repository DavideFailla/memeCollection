package org.generation.italy.memeCollection.model.data.abstractions;

import org.generation.italy.memeCollection.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
