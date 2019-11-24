package repository;

import io.ebean.Finder;
import models.Activity;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ActivityFinder {

    private static final Finder<Integer, Activity> FINDER = new Finder<>(Activity.class);

    public static List<Activity> findByCardId(Integer cardId){
        return FINDER.query().where().eq("card.id", cardId).findList();
    }
}
