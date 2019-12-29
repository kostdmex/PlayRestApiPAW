package converters.task;

import json.task.TaskListJsonPost;
import models.Card;
import models.TaskList;
import repository.CardFinder;

import java.util.function.Function;

public class TaskListJsonPostToTaskList implements Function<TaskListJsonPost, TaskList> {
    @Override
    public TaskList apply(TaskListJsonPost taskListJsonPost) {
        Card card = CardFinder.findCardById(taskListJsonPost.getCardId());
        return new TaskList(taskListJsonPost.getName(), card);
    }
}
