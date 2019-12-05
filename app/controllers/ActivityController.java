package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import json.activity.ActivityJson;
import json.activity.ActivityJsonPost;
import models.Board;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.BoardFinder;
import repository.ListFinder;
import service.ActivityService;
import service.AuthService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

public class ActivityController extends Controller {

    @Inject
    private AuthService authService;
    @Inject
    private ActivityService activityService;

    public Result createCardActivity(){
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }

        ActivityJsonPost activityJsonPost = Json.fromJson(request().body().asJson(), ActivityJsonPost.class);
        if(activityJsonPost.getCardId() == null){
            return badRequest();
        }
        if(authService.validateUserPermissionToCard(activityJsonPost.getCardId(), authService.getUserIdFromToken(request()))){
            return forbidden();
        }

        Integer activityId = activityService.createActivity(activityJsonPost);
        if(activityId == -1){
            return badRequest();
        }

        return created().withHeader("Location", String.valueOf(activityId));
    }

    public Result getActivitiesByCardId(Integer cardId){
        Board board = BoardFinder.findByListId(ListFinder.findByCardId(cardId).getId());
        Boolean isPublic = BoardFinder.checkIfBoardIsPublic(board.id);
        if(isPublic == null){
            return notFound();
        }

        if(!isPublic) {
            Result result = authService.validateRequest(request());
            if (result.status() == 403) {
                return result;
            }
            if (authService.validateUserPermissionToCard(cardId, authService.getUserIdFromToken(request()))) {
                return forbidden();
            }
        }
        List<ActivityJson> activityJsonList = activityService.getActivitiesByCardId(cardId);
        if(activityJsonList == null){
            return notFound();
        }

        return ok(Json.toJson(activityJsonList));
    }

}
