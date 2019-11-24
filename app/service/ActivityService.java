package service;

import converters.activity.ActivityJsonPostToActivity;
import converters.activity.ActivityToActivityJson;
import json.activity.ActivityJson;
import json.activity.ActivityJsonPost;
import models.Activity;
import repository.ActivityFinder;
import validator.ActivityValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ActivityService {

    @Inject
    private ActivityJsonPostToActivity activityJsonPostToActivity;
    @Inject
    private ActivityToActivityJson activityToActivityJson;

    public Integer createActivity(ActivityJsonPost activityJsonPost){
        if(!ActivityValidator.validateActivityPost(activityJsonPost)){
            return -1;
        }

        Activity activity = activityJsonPostToActivity.apply(activityJsonPost);
        activity.save();

        return activity.getId();
    }


    public List<ActivityJson> getActivitiesByCardId(Integer cardId){
        List<Activity> activities = ActivityFinder.findByCardId(cardId);
        if(activities.isEmpty()){
            return null;
        }

        return activities.stream().map(activityToActivityJson).collect(Collectors.toList());
    }
}
