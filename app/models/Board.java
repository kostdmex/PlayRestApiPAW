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

    @Column(name = "isPublic")
    private boolean isPublic;

    @OneToMany(mappedBy = "board")
    public List<models.List> lists;

    @ManyToMany(mappedBy = "boards")
    public List<User> users;

    public Board(String name, Integer team_id, String background, boolean isPublic) {
        this.name = name;
        this.team_id = team_id;
        this.background = background;
        this.isPublic = isPublic;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team_id=" + team_id +
                ", background='" + background + '\'' +
                ", isPublic=" + isPublic +
                '}';
    }
}
