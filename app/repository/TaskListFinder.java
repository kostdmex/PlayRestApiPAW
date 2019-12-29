package repository;

import io.ebean.Finder;
import models.TaskList;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TaskListFinder {

    private static final Finder<Integer, TaskList> FINDER = new Finder<>(TaskList.class);

    public static List<TaskList> findByCardId(Integer cardId) {
        return FINDER.query().where().eq("card_id", cardId).findList();
    }

    public static TaskList findById(Integer taskListId) {
        return FINDER.query().where().eq("id", taskListId).findOne();
    }

}
