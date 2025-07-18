package io.github.joaquimlcbfranco.mindmiles_backend.repository;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE first_name = ?1 or last_name = ?1 or username = ?1", nativeQuery = true)
    List<User> findUserBySearch(String searchWords);

}
