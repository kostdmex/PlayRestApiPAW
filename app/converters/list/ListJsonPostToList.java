package converters.list;

import json.list.ListJson;
import json.list.ListJsonPost;
import models.Board;
import models.List;
import repository.BoardFinder;

import java.util.function.Function;

public class ListJsonPostToList implements Function<ListJsonPost, List> {

    @Override
    public List apply(ListJsonPost listJson) {
        Board board = BoardFinder.findById(listJson.getBoardId());
        return new List(listJson.getName(), listJson.getNumberOnBoard(), board);
    }
}
