package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import json.task.*;
import models.Task;
import models.TaskList;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.BoardFinder;
import repository.CardFinder;
import repository.TaskFinder;
import repository.TaskListFinder;
import service.AuthService;
import service.TaskService;

import javax.inject.Inject;
import java.util.List;

public class TaskController extends Controller {

    @Inject
    private TaskService taskService;
    @Inject
    private AuthService authService;

    public Result getTaskListsByCardId(Integer cardId){
        if(CardFinder.findCardById(cardId) == null){
            return notFound();
        }
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }
        if(authService.validateUserPermissionToCard(cardId, authService.getUserIdFromToken(request()))){
            return forbidden();
        }

        List<TaskListJson> taskListJsons = taskService.getTaskListByCardId(cardId);

        if(taskListJsons == null){
            return notFound();
        }
        return ok(Json.toJson(taskListJsons));
    }

    public Result createTaskList(){
        Integer cardId;
        JsonNode body = request().body().asJson();
        if(body.hasNonNull("cardId")){
            cardId = body.get("cardId").asInt();
        }else{
            return badRequest();
        }
        if(CardFinder.findCardById(cardId) == null){
            return notFound();
        }
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }
        if(authService.validateUserPermissionToCard(cardId, authService.getUserIdFromToken(request()))){
            return forbidden();
        }

        Integer taskListId = taskService.createTaskList(Json.fromJson(request().body().asJson(), TaskListJsonPost.class));
        if(taskListId == null){
            return badRequest();
        }

        return created().withHeader("Location", String.valueOf(taskListId));
    }

    public Result createTask(){
        Integer taskListId;
        JsonNode body = request().body().asJson();
        if(body.hasNonNull("taskListId")){
            taskListId = body.get("taskListId").asInt();
        }else{
            return badRequest();
        }
        TaskList taskList = TaskListFinder.findById(taskListId);
        if(taskList == null){
            return notFound();
        }
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }
        if(authService.validateUserPermissionToCard(taskList.getCard().getId(), authService.getUserIdFromToken(request()))){
            return forbidden();
        }

        Integer taskId = taskService.createTask(Json.fromJson(request().body().asJson(), TaskJsonPost.class));
        if(taskId == null){
            return badRequest();
        }
        return created().withHeader("Location", String.valueOf(taskId));
    }

    public Result updateTaskList(Integer taskListId){
        TaskList taskList = TaskListFinder.findById(taskListId);
        if(taskList == null){
            return notFound();
        }
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }
        if(authService.validateUserPermissionToCard(taskList.getCard().getId(), authService.getUserIdFromToken(request()))){
            return forbidden();
        }

        taskService.updateTaskList(Json.fromJson(request().body().asJson(), TaskListJsonPut.class), taskList);
        return created();
    }

    public Result updateTask(Integer taskId){
        Task task = TaskFinder.findByTaskId(taskId);
        if(task == null){
            return notFound();
        }
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }
        if(authService.validateUserPermissionToCard(task.getTaskList().getCard().getId(), authService.getUserIdFromToken(request()))){
            return forbidden();
        }

        taskService.updateTask(Json.fromJson(request().body().asJson(), TaskJsonPut.class), task);
        return created();
    }

    public Result deleteTask(Integer taskId){
        Task task = TaskFinder.findByTaskId(taskId);
        if(task == null){
            return notFound();
        }
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }
        if(authService.validateUserPermissionToCard(task.getTaskList().getCard().getId(), authService.getUserIdFromToken(request()))){
            return forbidden();
        }

        taskService.deleteTask(task);
        return ok();
    }

    public Result deleteTaskList(Integer taskListId){
        TaskList taskList = TaskListFinder.findById(taskListId);
        if(taskList == null){
            return notFound();
        }
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }
        if(authService.validateUserPermissionToCard(taskList.getCard().getId(), authService.getUserIdFromToken(request()))){
            return forbidden();
        }

        taskService.deleteTaskList(taskList);
        return ok();
    }

}
