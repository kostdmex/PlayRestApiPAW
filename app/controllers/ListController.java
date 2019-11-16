package controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import json.list.ListJson;
import json.list.ListJsonPost;
import json.list.ListJsonPut;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.ListService;

import java.util.List;

@Singleton
public class ListController extends Controller {

    @Inject
    private ListService listService;

    public Result findByBoardId(Integer boardId){
        List<ListJson> listJsons = listService.findByBoardId(boardId);

        if(listJsons == null){
            return notFound();
        }

        return ok(Json.toJson(listJsons));
    }

    public Result createList(){
        Integer listId = listService.createList(Json.fromJson(request().body().asJson(), ListJsonPost.class));
        if(listId == null){
            return badRequest("Given boardId not exists");
        }

        return created().withHeader("Location", String.valueOf(listId));
    }

    public Result updateList(Integer listId){
        boolean success = listService.updateList(listId, Json.fromJson(request().body().asJson(), ListJsonPut.class));
        if(!success){
            return notFound();
        }

        return created();
    }
}
