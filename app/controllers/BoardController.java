package controllers;

import json.board.BoardJson;
import json.board.BoardJsonPost;
import json.board.BoardJsonPut;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.BoardService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class BoardController extends Controller {

    @Inject
    private BoardService boardService;

    public Result findById(Integer boardId) {
        BoardJson boardJson = boardService.findByBoardId(boardId);
        if (boardJson == null) {
            return notFound();
        }
        return ok(Json.toJson(boardJson));
    }

    public Result findAllTeamBoards(Integer teamId) {
        List<BoardJson> boards = boardService.findBoardsByTeamId(teamId);
        if (boards == null) {
            return notFound();
        }

        return ok(Json.toJson(boards));
    }

    public Result createBoard() {
        BoardJsonPost boardJsonPost = Json.fromJson(request().body().asJson(), BoardJsonPost.class);
        Integer boardId = boardService.createBoard(boardJsonPost);

        if(boardId == null){
            return badRequest();
        }

        return created().withHeader("Location", String.valueOf(boardId));
    }

    public Result updateBoard(Integer boardId) {

        BoardJsonPut boardJsonPut = Json.fromJson(request().body().asJson(), BoardJsonPut.class);
        boolean success = boardService.updateBoard(boardId, boardJsonPut);
        if(!success)
            return notFound();

        return created();
    }

}
