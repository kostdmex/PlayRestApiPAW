package converters.task;

import json.task.TaskJson;
import models.Task;

import java.util.function.Function;

public class TaskToTaskJson implements Function<Task, TaskJson> {

    @Override
    public TaskJson apply(Task task) {
        return new TaskJson(task.getId(), task.getName(), task.isDone(), task.getTaskList().getId());
    }

}
