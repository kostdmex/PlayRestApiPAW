package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Board extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public Integer team_id;
    public String background;

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
