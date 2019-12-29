package repository;

import io.ebean.Finder;
import models.Task;

import java.util.List;

public class TaskFinder {

    private static final Finder<Integer, Task> FINDER = new Finder<>(Task.class);

    public static List<Task> findByTaskListId(Integer taskListId) {
        return FINDER.query().where().eq("task_list_id", taskListId).findList();
    }

    public static Task findByTaskId(Integer taskId) {
        return FINDER.query().where().eq("id", taskId).findOne();
    }

}
