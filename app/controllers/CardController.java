package controllers;

import json.card.CardJson;
import json.card.CardJsonPost;
import json.card.CardJsonPut;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.CardService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CardController extends Controller {

    @Inject
    private CardService cardService;

    public Result getCardsBtListId(Integer listId){
        List<CardJson> cardJsonList =  cardService.getCardsByListId(listId);
        if(cardJsonList == null){
            return notFound();
        }
        return ok(Json.toJson(cardJsonList));
    }

    public Result getCardById(Integer cardId){
        CardJson cardJson = cardService.getCardById(cardId);
        if(cardJson == null){
            return notFound();
        }
        return ok(Json.toJson(cardJson));
    }

    public Result createCard(){
        Integer cardId = cardService.createCard(Json.fromJson(request().body().asJson(), CardJsonPost.class));
        if(cardId == null){
            return badRequest();
        }

        return created().withHeader("Location", String.valueOf(cardId));
    }

    public Result updateCard(Integer cardId){
        boolean success = cardService.updateCard(cardId, Json.fromJson(request().body().asJson(), CardJsonPut.class));
        if(!success){
            return badRequest();
        }

        return created();
    }
}
