package converters.list;

import json.list.ListJson;
import models.Board;
import models.List;
import repository.BoardFinder;

import java.util.function.Function;

public class ListJsonToList implements Function<ListJson, List> {

    @Override
    public List apply(ListJson listJson) {
        Board board = BoardFinder.findById(listJson.boardId);
        return new List(listJson.name, listJson.numberOnBoard, board);
    }
}
