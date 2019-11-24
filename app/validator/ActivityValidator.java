package validator;

import json.activity.ActivityJsonPost;
import repository.CardFinder;
import repository.UserFinder;

import javax.inject.Singleton;

@Singleton
public class ActivityValidator {

    public static boolean validateActivityPost(ActivityJsonPost activityJsonPost){
        if(activityJsonPost.getActivityText() == null || activityJsonPost.getCardId() == null || activityJsonPost.getUserId() == null){
            return false;
        }

        if(CardFinder.findCardById(activityJsonPost.getCardId()) == null || UserFinder.findById(activityJsonPost.getUserId()) == null){
            return false;
        }

        return true;
    }

}
