package converters.activity;

import converters.card.CardToCardJson;
import json.activity.ActivityJson;
import json.card.CardJson;
import models.Activity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Date;
import java.time.ZoneId;
import java.util.function.Function;

@Singleton
public class ActivityToActivityJson implements Function<Activity, ActivityJson> {

    @Inject
    private CardToCardJson cardToCardJson;

    @Override
    public ActivityJson apply(Activity activity) {
        CardJson cardJson = cardToCardJson.apply(activity.getCard());
        return new ActivityJson(Date.from(activity.getAddDate().atZone(ZoneId.systemDefault()).toInstant()),
                cardJson, activity.getActivityText(), activity.getUser().getName(), activity.getUser().getSurname());
    }
}
