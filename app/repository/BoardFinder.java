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
}
