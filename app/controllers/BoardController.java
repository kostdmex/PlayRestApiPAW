package controllers;

import json.board.BoardJson;
import json.board.BoardJsonPost;
import json.board.BoardJsonPut;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.BoardFinder;
import service.AuthService;
import service.BoardService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class BoardController extends Controller {

    @Inject
    private BoardService boardService;

    @Inject
    private AuthService authService;


    public Result findById(Integer boardId) {
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }

        BoardJson boardJson = boardService.findByBoardId(boardId, authService.getUserIdFromToken(request()));
        if (boardJson == null) {
            return notFound();
        }
        return ok(Json.toJson(boardJson));
    }

    public Result findAll(){
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }

        List<BoardJson> boards = boardService.findAll(authService.getUserIdFromToken(request()));
        if (boards == null) {
            return notFound();
        }

        return ok(Json.toJson(boards));
    }

    public Result findAllTeamBoards(Integer teamId) {

        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }

        System.out.println(authService.getUserIdFromToken(request()));

        System.out.println(BoardFinder.findByUserId(authService.getUserIdFromToken(request())));

        List<BoardJson> boards = boardService.findBoardsByTeamId(teamId);
        if (boards == null) {
            return notFound();
        }

        return ok(Json.toJson(boards));
    }

    public Result createBoard() {
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }

        BoardJsonPost boardJsonPost = Json.fromJson(request().body().asJson(), BoardJsonPost.class);
        Integer boardId = boardService.createBoard(boardJsonPost, authService.getUserIdFromToken(request()));

        if(boardId == null){
            return badRequest();
        }

        return created().withHeader("Location", String.valueOf(boardId));
    }

    public Result updateBoard(Integer boardId) {
        Result result = authService.validateRequest(request());
        if(result.status() == 403){
            return result;
        }

        BoardJsonPut boardJsonPut = Json.fromJson(request().body().asJson(), BoardJsonPut.class);
        boolean success = boardService.updateBoard(boardId, boardJsonPut, authService.getUserIdFromToken(request()));
        if(!success)
            return notFound();

        return created();
    }


    public Result findByUserAndBoardName(String userName, String boardName){
        List<BoardJson> list = boardService.findBoardsByUserNameAndBoardsName(userName, boardName);
        if (list == null) {
            return notFound();
        }

        return ok(Json.toJson(list));
    }
}
