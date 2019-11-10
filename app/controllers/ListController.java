package controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import json.ListJson;
import play.libs.Json;
import play.mvc.Result;
import service.ListService;

import java.util.List;

import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;

@Singleton
public class ListController {

    @Inject
    private ListService listService;

    public Result findByBoardId(Integer boardId){
        List<ListJson> listJsons = listService.findByBoardId(boardId);

        if(listJsons == null){
            return notFound();
        }

        return ok(Json.toJson(listJsons));
    }
}
