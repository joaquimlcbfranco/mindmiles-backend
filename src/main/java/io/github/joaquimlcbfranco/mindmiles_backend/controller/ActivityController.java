package io.github.joaquimlcbfranco.mindmiles_backend.controller;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.Activity;
import io.github.joaquimlcbfranco.mindmiles_backend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ActivityController {

    private ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/activities")
    public List<Activity> getActivities() {
        return this.activityService.getActivities();
    }

    @GetMapping("/activities/{id}")
    public Activity getActivity(@PathVariable long id) {
        return this.activityService.getActivityById(id);
    }

    @GetMapping("/users/{userId}/activities")
    public List<Activity> getAllUserActivities(@PathVariable long userId) {
        return this.activityService.getActivityByUserId(userId);
    }

    @GetMapping("/users/{userId}/activities/{activityId}")
    public Activity getUserActivity(@PathVariable long userId, @PathVariable long activityId) {
        return this.activityService.getActivityByUserIdAndActivityId(userId, activityId);
    }

    @PostMapping("/users/{userId}/activities")
    public Activity addUserActivity(@PathVariable long userId, @RequestBody Activity newActivity) {
        return this.activityService.addActivity(userId, newActivity);
    }

    @PutMapping("/activities/{id}")
    public Activity updateActivity(@PathVariable long id, @RequestBody Activity newActivity) {
        return this.activityService.updateActivity(id, newActivity);
    }

    @DeleteMapping("/activities/{id}")
    public String deleteActivity(@PathVariable long id) {
        return this.activityService.deleteActivity(id);
    }
}
