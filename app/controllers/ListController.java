package controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import json.list.ListJson;
import json.list.ListJsonPost;
import json.list.ListJsonPut;
import json.list.ListJsonPutOrder;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.BoardFinder;
import service.AuthService;
import service.ListService;

import java.io.IOException;
import java.util.List;

@Singleton
public class ListController extends Controller {

    @Inject
    private ListService listService;

    @Inject
    private AuthService authService;


    public Result findByBoardId(Integer boardId){
        Boolean isPublic = BoardFinder.checkIfBoardIsPublic(boardId);
        if(isPublic == null){
            return notFound();
        }

        if(!isPublic) {
            Result result = authService.validateRequest(request());
            if (result.status() == 403) {
                return result;
            }

            if (BoardFinder.findByUserIdAndBoardId(authService.getUserIdFromToken(request()), boardId) == null) {
                return forbidden();
            }

        }

        List<ListJson> listJsons = listService.findByBoardId(boardId);

        if(listJsons == null){
            return notFound();
        }

        return ok(Json.toJson(listJsons));
    }

    public Result createList(){
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }

        JsonNode body = request().body().asJson();
        if(body.hasNonNull("boardId")){
            Integer boardId = body.get("boardId").asInt();
            if(BoardFinder.findByUserIdAndBoardId(authService.getUserIdFromToken(request()), boardId) == null){
                return forbidden();
            }
        }else{
            return badRequest();
        }


        Integer listId = listService.createList(Json.fromJson(request().body().asJson(), ListJsonPost.class));
        if(listId == null){
            return badRequest("Given boardId not exists");
        }

        return created().withHeader("Location", String.valueOf(listId));
    }

    public Result updateList(Integer listId){
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }

        boolean success = listService.updateList(listId, Json.fromJson(request().body().asJson(), ListJsonPut.class), authService.getUserIdFromToken(request()));
        if(!success){
            return notFound();
        }

        return created();
    }

    public Result setListOrder(Integer boardId) throws IOException {
        Result result = authService.validateRequest(request());
        if (result.status() == 403) {
            return result;
        }
        if (BoardFinder.findByUserIdAndBoardId(authService.getUserIdFromToken(request()), boardId) == null) {
            return forbidden();
        }

        List<ListJsonPutOrder> listJsonPutOrders = new ObjectMapper().readValue(request().body().asJson().toString()
                , TypeFactory.defaultInstance().constructCollectionType(List.class,
                        ListJsonPutOrder.class));

        boolean success = listService.setListOrder(boardId, listJsonPutOrders);

        if(!success){
            return badRequest();
        }

        return ok();
    }

}
