package controllers;

import json.comment.CommentJson;
import json.comment.CommentJsonPost;
import models.Board;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.BoardFinder;
import repository.CardFinder;
import repository.ListFinder;
import service.AuthService;
import service.CommentService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CommentController extends Controller {

    @Inject
    private CommentService commentService;
    @Inject
    private AuthService authService;

    public Result addCommentToCard(Integer cardId){
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }
        if(authService.validateUserPermissionToCard(cardId, authService.getUserIdFromToken(request()))){
            return forbidden();
        }

        CommentJsonPost commentJsonPost = Json.fromJson(request().body().asJson(), CommentJsonPost.class);
        Integer commentId = commentService.addCommentToCard(commentJsonPost);
        if(commentId == -1){
            return badRequest();
        }


        return created().withHeader("Location", String.valueOf(commentId));
    }

    public Result getCommentsByCardId(Integer cardId){
        if(CardFinder.findCardById(cardId) == null){
            return notFound();
        }

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

        List<CommentJson> commentJsons = commentService.getCommentsByCardId(cardId);
        if(commentJsons == null){
            return notFound();
        }

        return ok(Json.toJson(commentJsons));
    }
}
