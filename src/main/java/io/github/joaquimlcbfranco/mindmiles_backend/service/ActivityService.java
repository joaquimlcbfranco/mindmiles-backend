package io.github.joaquimlcbfranco.mindmiles_backend.service;

import io.github.joaquimlcbfranco.mindmiles_backend.entity.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> getActivities();

    Activity getActivityById(long id);

    List<Activity> getActivityByUserId(long id);

    Activity getActivityByUserIdAndActivityId(long userId, long activityId);

    Activity addActivity(long userId, Activity newActivity);

    Activity updateActivity(long id, Activity newActivity);

    String deleteActivity(long id);

}
