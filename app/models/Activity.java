package models;

import io.ebean.Model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Activity extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "add_date")
    private LocalDateTime addDate;

    @Column(name = "text_activity")
    private String activityText;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public String getActivityText() {
        return activityText;
    }

    public void setActivityText(String activityText) {
        this.activityText = activityText;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity(LocalDateTime addDate, String activityText, Card card, User user) {
        this.addDate = addDate;
        this.activityText = activityText;
        this.card = card;
        this.user = user;
    }
}
