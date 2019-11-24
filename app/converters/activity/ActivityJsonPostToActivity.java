package converters.activity;

import json.activity.ActivityJsonPost;
import models.Activity;
import models.Card;
import models.User;
import repository.CardFinder;
import repository.UserFinder;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.function.Function;

@Singleton
public class ActivityJsonPostToActivity implements Function<ActivityJsonPost, Activity> {

    @Override
    public Activity apply(ActivityJsonPost activityJsonPost) {
        Card card = CardFinder.findCardById(activityJsonPost.getCardId());
        User user = UserFinder.findById(activityJsonPost.getUserId());
        LocalDateTime localDateTime = null;
        if(activityJsonPost.getAddDate() == null){
            localDateTime = LocalDateTime.now();
        }else{
            localDateTime = activityJsonPost.getAddDate();
        }
        return new Activity(localDateTime, activityJsonPost.getActivityText(), card, user);
    }
}
