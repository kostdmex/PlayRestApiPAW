package controllers;

import json.BoardJson;
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

//    public Result save() {
//
//        BoardJson boardJson = Json.fromJson(request().body().asJson(), BoardJson.class);
//
//        Board board = new Board();
//        board.id = boardJson.id;
//        board.name = boardJson.name;
//        board.team_id = boardJson.team_id;
//        board.background = boardJson.background;
//        board.save();
//        return ok(Json.toJson(boardToBoardJson.apply(board)));
//
//    }

}
