package models;

import io.ebean.Model;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Card extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    @Column(name = "label_id")
    private Integer labelId;

    @Column(name = "number_on_list")
    private Integer numberOnList;

    @Column(name = "add_date")
    private LocalDateTime addDate;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    public List list;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", labelId=" + labelId +
                ", numberOnList=" + numberOnList +
                ", addDate=" + addDate +
                ", list=" + list.getId() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getNumberOnList() {
        return numberOnList;
    }

    public void setNumberOnList(Integer numberOnList) {
        this.numberOnList = numberOnList;
    }

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Card() {
    }

    public Card(String title, String description, Integer labelId, Integer numberOnList, LocalDateTime addDate, List list) {
        this.title = title;
        this.description = description;
        this.labelId = labelId;
        this.numberOnList = numberOnList;
        this.addDate = addDate;
        this.list = list;
    }

}
