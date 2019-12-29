package service;

import converters.task.TaskJsonPostToTask;
import converters.task.TaskListJsonPostToTaskList;
import converters.task.TaskListToTaskListJson;
import io.ebean.Model;
import json.task.*;
import models.Task;
import models.TaskList;
import repository.TaskFinder;
import repository.TaskListFinder;
import validator.TaskValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class TaskService {

    @Inject
    private TaskListToTaskListJson taskListToTaskListJson;
    @Inject
    private TaskListJsonPostToTaskList taskListJsonPostToTaskList;
    @Inject
    private TaskJsonPostToTask taskJsonPostToTask;

    public List<TaskListJson> getTaskListByCardId(Integer cardId) {
        List<TaskList> taskLists = TaskListFinder.findByCardId(cardId);
        if(taskLists.isEmpty()){
            return null;
        }
        return taskLists
                .stream()
                .map(taskListToTaskListJson)
                .collect(Collectors.toList());
    }

    public Integer createTaskList(TaskListJsonPost taskListJsonPost) {
        if(!TaskValidator.validateTaskListJsonPost(taskListJsonPost)){
            return null;
        }

        TaskList taskList = taskListJsonPostToTaskList.apply(taskListJsonPost);
        taskList.save();
        return taskList.getId();
    }

    public Integer createTask(TaskJsonPost taskJsonPost) {
        if(!TaskValidator.validateTaskJsonPost(taskJsonPost)){
            return null;
        }

        Task task = taskJsonPostToTask.apply(taskJsonPost);
        task.save();
        return task.getId();
    }

    public void updateTaskList(TaskListJsonPut taskListJsonPut, TaskList taskList) {
        if(taskListJsonPut.getName() != null){
            taskList.setName(taskListJsonPut.getName());
        }

        taskList.save();
    }

    public void updateTask(TaskJsonPut taskJsonPut, Task task) {
        if(taskJsonPut.getName() != null){
            task.setName(taskJsonPut.getName());
        }
        if(taskJsonPut.isDone() != null){
            task.setDone(taskJsonPut.isDone());
        }
        task.save();
    }

    public void deleteTask(Task task) {
        task.delete();
    }

    public void deleteTaskList(TaskList taskList) {
        List<Task> tasks = TaskFinder.findByTaskListId(taskList.getId());
        tasks.forEach(Model::delete);
        taskList.delete();
    }
}
