package converters;

import json.ListJson;
import models.List;

import javax.inject.Singleton;
import java.util.function.Function;

@Singleton
public class ListToListJson implements Function<List, ListJson> {

    @Override
    public ListJson apply(List list) {
        return new ListJson(list.id, list.name, list.boardId, list.numberOnBoard);
    }
}
