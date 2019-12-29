package converters.task;

import json.task.TaskJsonPost;
import models.Task;
import models.TaskList;
import repository.TaskListFinder;

import java.util.function.Function;

public class TaskJsonPostToTask implements Function<TaskJsonPost,Task> {
    @Override
    public Task apply(TaskJsonPost taskJsonPost) {
        TaskList taskList = TaskListFinder.findById(taskJsonPost.getTaskListId());
        return new Task(taskJsonPost.getName(),taskList);
    }
}
