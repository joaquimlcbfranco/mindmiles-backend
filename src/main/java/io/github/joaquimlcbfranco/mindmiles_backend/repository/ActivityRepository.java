package io.github.joaquimlcbfranco.mindmiles_backend.repository;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query(value = "SELECT * FROM activities WHERE user_id = ?1", nativeQuery = true)
    List<Activity> findAllActivitiesByUserId(long userId);

    @Query(value = "SELECT * FROM activities WHERE user_id = ?1 and id = ?2", nativeQuery = true)
    Optional<Activity> findActivityByUserIdAndActivityId(long userId, long activityId);
}
