package converters.task;

import json.task.TaskListJson;
import models.Card;
import models.Task;
import models.TaskList;
import repository.CardFinder;
import repository.TaskFinder;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TaskListToTaskListJson implements Function<TaskList, TaskListJson> {

    @Inject
    private TaskToTaskJson taskToTaskJson;

    @Override
    public TaskListJson apply(TaskList taskList) {
        Card card = CardFinder.findCardById(taskList.getCard().getId());
        List<Task> task = TaskFinder.findByTaskListId(taskList.getId());
        return new TaskListJson(taskList.getId(), taskList.getName(), card.getId(), task.stream().map(taskToTaskJson).collect(Collectors.toList()));
    }

}
