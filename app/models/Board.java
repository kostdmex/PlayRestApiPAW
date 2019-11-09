package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Board extends Model {

    public static final Finder<Integer, Board> FINDER = new Finder<>(Board.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public Integer team_id;
    public String background;

    public static Board findById(Integer id) {
        return FINDER.ref(id);
    }

    public static List<Board> findAllTeamBoards(Integer team_id) {
        return FINDER.query().where().eq("team_id", team_id).findList();
    }

}
