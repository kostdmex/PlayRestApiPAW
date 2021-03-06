package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import json.card.CardJson;
import json.card.CardJsonPost;
import json.card.CardJsonPut;
import json.card.CardJsonPutOrder;
import models.Board;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.BoardFinder;
import repository.ListFinder;
import service.AuthService;
import service.CardService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;

public class CardController extends Controller {

    @Inject
    private CardService cardService;
    @Inject
    private AuthService authService;

    public Result getCardsByListId(Integer listId){
        Board board = BoardFinder.findByListId(listId);
        if(board == null){
            return notFound();
        }
        Boolean isPublic = BoardFinder.checkIfBoardIsPublic(board.id);
        if(isPublic == null){
            return notFound();
        }

        if(!isPublic) {
            Result result = authService.validateRequest(request());
            if (result.status() == 403) {
                return result;
            }

            if (authService.validateUserPermissionToList(listId, authService.getUserIdFromToken(request()))) {
                return forbidden();
            }
        }
        List<CardJson> cardJsonList =  cardService.getCardsByListId(listId);
        if(cardJsonList == null){
            return notFound();
        }
        return ok(Json.toJson(cardJsonList));
    }

    public Result getCardById(Integer cardId){
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
            if(authService.validateUserPermissionToCard(cardId, authService.getUserIdFromToken(request()))){
                return forbidden();
            }

        }
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

    public Result setCardOrder(Integer listId) throws IOException {
        Board board = BoardFinder.findByListId(listId);
        if(board == null){
            return notFound();
        }
        Boolean isPublic = BoardFinder.checkIfBoardIsPublic(board.id);
        if(isPublic == null){
            return notFound();
        }

        if(!isPublic) {
            Result result = authService.validateRequest(request());
            if (result.status() == 403) {
                return result;
            }

            if (authService.validateUserPermissionToList(listId, authService.getUserIdFromToken(request()))) {
                return forbidden();
            }
        }

        List<CardJsonPutOrder> cardJsonPutOrders = new ObjectMapper().readValue(request().body().asJson().toString()
                , TypeFactory.defaultInstance().constructCollectionType(List.class,
                        CardJsonPutOrder.class));

        boolean success = cardService.setCardOrder(listId, cardJsonPutOrders);

        if(!success){
            return badRequest();
        }

        return ok();
    }
}
