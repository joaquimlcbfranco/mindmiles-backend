package io.github.joaquimlcbfranco.mindmiles_backend.repository;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {


}
