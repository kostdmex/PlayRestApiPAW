package controllers;

import converters.BoardToBoardJson;
import json.BoardJson;
import model.Board;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class BoardController extends Controller {

    @Inject
    private BoardToBoardJson boardToBoardJson;

    public Result findById(Integer id) {

        return Optional.ofNullable(Board.findById(id)).map((boardToBoardJson)).map(json -> ok(Json.toJson(json))).orElse(notFound());

    }

    public Result findAll() {

        return ok(Json.toJson(Board.findAll().stream().map(boardToBoardJson).collect(Collectors.toList())));

    }

    public Result save() {

        BoardJson boardJson = Json.fromJson(request().body().asJson(), BoardJson.class);

        Board board = new Board();
        board.id = boardJson.id;
        board.name = boardJson.name;
        board.team_id = boardJson.team_id;
        board.background = boardJson.background;
        board.save();
        return ok(Json.toJson(boardToBoardJson.apply(board)));

    }

}
