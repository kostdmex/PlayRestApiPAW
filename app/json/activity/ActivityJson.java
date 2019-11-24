package json.activity;

import json.card.CardJson;

import java.util.Date;
import java.time.LocalDateTime;

public class ActivityJson {

    private Date date;
    private CardJson card;
    private String activityText;
    private String userName;
    private String userSurname;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CardJson getCard() {
        return card;
    }

    public void setCard(CardJson card) {
        this.card = card;
    }

    public String getActivityText() {
        return activityText;
    }

    public void setActivityText(String activityText) {
        this.activityText = activityText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public ActivityJson(Date date, CardJson card, String activityText, String userName, String userSurname) {
        this.date = date;
        this.card = card;
        this.activityText = activityText;
        this.userName = userName;
        this.userSurname = userSurname;
    }
}
