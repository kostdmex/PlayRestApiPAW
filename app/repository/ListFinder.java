package repository;

import io.ebean.Finder;
import models.List;

import javax.inject.Singleton;

@Singleton
public class ListFinder {

    private static final Finder<Integer, List> FINDER = new Finder<>(List.class);

    public static java.util.List<List> findAllListsByBoardId(Integer boardId) {
        return FINDER.query().where().eq("board_id", boardId).findList();
    }

    public static List findListById(Integer listId){
        return FINDER.query().where().eq("id", listId).findOne();
    }

}
