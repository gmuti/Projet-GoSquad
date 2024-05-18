package com.gosquad.GoSquad.service;

import com.gosquad.GoSquad.entity.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> getActivities();

    Boolean createActivity(Activity activity);

    Boolean updateActivity(Activity activity);

    Boolean deleteActivity(String id);
}
