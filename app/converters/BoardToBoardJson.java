package converters;

import json.BoardJson;
import models.Board;

import javax.inject.Singleton;
import java.util.function.Function;

@Singleton
public class BoardToBoardJson implements Function<Board, BoardJson> {

    @Override
    public BoardJson apply(Board board) {
        return new BoardJson(board.id, board.name, board.team_id, board.background);
    }

}
