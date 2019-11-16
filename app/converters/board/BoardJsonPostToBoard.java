package converters.board;

import json.board.BoardJsonPost;
import models.Board;

import javax.inject.Singleton;
import java.util.function.Function;

@Singleton
public class BoardJsonPostToBoard implements Function<BoardJsonPost, Board> {

    @Override
    public Board apply(BoardJsonPost boardJsonPost) {
        return new Board(boardJsonPost.name, boardJsonPost.team_id, boardJsonPost.background);
    }
}
