package io.github.joaquimlcbfranco.mindmiles_backend.repository;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
