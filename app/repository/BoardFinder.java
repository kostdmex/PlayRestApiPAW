package repository;

import io.ebean.Finder;
import models.Board;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class BoardFinder {

    private static final Finder<Integer, Board> FINDER = new Finder<>(Board.class);

    public static Board findById(Integer id) {
        return FINDER.query().where().eq("id", id).findOne();
    }

    public static List<Board> findAllTeamBoards(Integer team_id) {
        return FINDER.query().where().eq("team_id", team_id).findList();
    }

    public static  List<Board> findByUserId(Integer userId){
        return FINDER.query().where().eq("users.id", userId).findList();
    }

    public static  Board findByUserIdAndBoardId(Integer userId, Integer boardId){
        return FINDER.query().where().eq("id", boardId).eq("users.id", userId).findOne();
    }

    public static  Board findByListId(Integer listId){
        return FINDER.query().where().eq("lists.id", listId).findOne();
    }
}
