package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import json.card.CardJson;
import json.card.CardJsonPost;
import json.card.CardJsonPut;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.BoardFinder;
import repository.ListFinder;
import service.AuthService;
import service.CardService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CardController extends Controller {

    @Inject
    private CardService cardService;
    @Inject
    private AuthService authService;

    public Result getCardsBtListId(Integer listId){
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }
        authService.validateUserPermissionToList(listId, authService.getUserIdFromToken(request()));

        List<CardJson> cardJsonList =  cardService.getCardsByListId(listId);
        if(cardJsonList == null){
            return notFound();
        }
        return ok(Json.toJson(cardJsonList));
    }

    public Result getCardById(Integer cardId){
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }

        authService.validateUserPermissionToCard(cardId, authService.getUserIdFromToken(request()));

        CardJson cardJson = cardService.getCardById(cardId);
        if(cardJson == null){
            return notFound();
        }
        return ok(Json.toJson(cardJson));
    }

    public Result createCard(){
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }

        JsonNode body = request().body().asJson();
        if(body.hasNonNull("listId")){
            Integer listId = body.get("listId").asInt();
            if(BoardFinder.findByUserIdAndBoardId(authService.getUserIdFromToken(request()), ListFinder.findListById(listId).getBoard().id) == null){
                return forbidden();
            }
        }else{
            return badRequest();
        }

        Integer cardId = cardService.createCard(Json.fromJson(request().body().asJson(), CardJsonPost.class));
        if(cardId == null){
            return badRequest();
        }

        return created().withHeader("Location", String.valueOf(cardId));
    }

    public Result updateCard(Integer cardId){
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }
        boolean success = cardService.updateCard(cardId, Json.fromJson(request().body().asJson(), CardJsonPut.class), authService.getUserIdFromToken(request()));
        if(!success){
            return badRequest();
        }

        return created();
    }
}
