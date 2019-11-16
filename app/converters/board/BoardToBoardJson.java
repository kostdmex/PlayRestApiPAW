package converters.board;

import converters.list.ListToListJson;
import json.list.ListJson;
import json.board.BoardJson;
import models.Board;
import models.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.function.Function;
import java.util.stream.Collectors;

@Singleton
public class BoardToBoardJson implements Function<Board, BoardJson> {

    @Inject
    private ListToListJson listToListJson;

    @Override
    public BoardJson apply(Board board) {

        java.util.List<List> lists = board.lists;

        java.util.List<ListJson> listJsons = lists.stream().map(listToListJson::apply).collect(Collectors.toList());

        return new BoardJson(board.id, board.name, board.team_id, board.background, listJsons);
    }

}
