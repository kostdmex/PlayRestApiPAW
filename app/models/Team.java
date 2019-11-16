package models;

import io.ebean.Model;

import javax.persistence.*;

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
}
