package io.github.joaquimlcbfranco.mindmiles_backend.service;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.Activity;
import io.github.joaquimlcbfranco.mindmiles_backend.entity.User;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.ActivityNotFoundException;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.InvalidParametersException;
import io.github.joaquimlcbfranco.mindmiles_backend.exception.UserNotFoundException;
import io.github.joaquimlcbfranco.mindmiles_backend.repository.ActivityRepository;
import io.github.joaquimlcbfranco.mindmiles_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {

    private ActivityRepository activityRepository;
    private UserRepository userRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Activity> getActivities() {
        return this.activityRepository.findAll();
    }

    @Override
    public Activity getActivityById(long id) {
        Optional<Activity> foundActivity = this.activityRepository.findById(id);

        if (foundActivity.isPresent()) {
            Activity activity = foundActivity.get();
            return activity;
        }

        throw new ActivityNotFoundException("Activity with id " + id + " not found");
    }

    @Override
    public List<Activity> getActivityByUserId(long id) {
        List<Activity> foundActivity = this.activityRepository.findAllActivitiesByUserId(id);

        if (foundActivity.isEmpty()) {
            return null;
        }

        return foundActivity;
    }

    @Override
    public Activity getActivityByUserIdAndActivityId(long userId, long activityId) {
        Optional<Activity> foundActivity = this.activityRepository.findActivityByUserIdAndActivityId(userId, activityId);

        if (foundActivity.isPresent()) {
            Activity activity = foundActivity.get();
            return activity;
        }

        throw new ActivityNotFoundException("Activity with id " + activityId + " not found");
    }

    @Override
    @Transactional
    public Activity addActivity(long userId, Activity newActivity) {
        validateActivity(newActivity.getTitle(), newActivity.getDescription(), newActivity.getDate(),newActivity.getHours(), newActivity.getMinutes(), newActivity.getSeconds());

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        Activity savedActivity = this.activityRepository.save(newActivity);
        user.addActivity(savedActivity);

        return savedActivity;
    }

    @Override
    @Transactional
    public Activity updateActivity(long id, Activity newActivity) {
        validateActivity(newActivity.getTitle(), newActivity.getDescription(), newActivity.getDate(),newActivity.getHours(), newActivity.getMinutes(), newActivity.getSeconds());

        Activity foundActivity = this.activityRepository.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException("Activity with id " + id + " not found"));

        foundActivity.setTitle(newActivity.getTitle());
        foundActivity.setDescription(newActivity.getDescription());
        foundActivity.setDate(newActivity.getDate());
        foundActivity.setHours(newActivity.getHours());
        foundActivity.setMinutes(newActivity.getMinutes());
        foundActivity.setSeconds(newActivity.getSeconds());

        return foundActivity;
    }

    @Override
    @Transactional
    public String deleteActivity(long id) {

        Activity foundActivity = this.activityRepository.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException("Activity with id " + id + " not found"));

        this.activityRepository.delete(foundActivity);

        return "Activity with id " + id + " deleted";
    }

    public static boolean validateActivity(String title, String description, LocalDate date, long hours, long minutes, long seconds) {

        if (title == null || title.length() > 50) {
            throw new InvalidParametersException("Title is a required field and must be less than 50 characters long");
        }

        if (description != null && description.length() > 1500) {
            throw new InvalidParametersException("Description must be less than 1500 characters long");
        }

        if (date == null) {
            throw new InvalidParametersException("Date is a required field");
        }

        if (Long.valueOf(hours) == null) {
            throw new InvalidParametersException("Invalid amount of study hours (hours)");
        }

        if (Long.valueOf(minutes) == null) {
            throw new InvalidParametersException("Invalid amount of study hours (minutes)");
        }

        if (Long.valueOf(seconds) == null) {
            throw new InvalidParametersException("Invalid amount of study hours (seconds)");
        }

        return true;
    }
}
