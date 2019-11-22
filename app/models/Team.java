package models;

import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public String name_short;
    public String description;
    public String site;
    public String logo;

    @ManyToMany(mappedBy = "boards")
    public List<User> teams;

}
