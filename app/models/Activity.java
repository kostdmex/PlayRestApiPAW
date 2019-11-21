package models;

import io.ebean.Model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class Activity extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "add_date")
    private LocalDateTime addDate;

    @Column(name = "text_activity")
    private String activityText;

}
