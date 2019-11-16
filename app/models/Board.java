package models;

import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Board extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public Integer team_id;
    public String background;

    @OneToMany(mappedBy = "board")
    public List<models.List> lists;

    public Board(String name, Integer team_id, String background) {
        this.name = name;
        this.team_id = team_id;
        this.background = background;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
