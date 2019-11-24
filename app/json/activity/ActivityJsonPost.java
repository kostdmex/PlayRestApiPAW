package json.activity;

import java.time.LocalDateTime;

public class ActivityJsonPost {

    private Integer userId;
    private Integer cardId;
    private LocalDateTime addDate;
    private String activityText;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
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

    public ActivityJsonPost(Integer userId, Integer cardId, LocalDateTime addDate, String activityText) {
        this.userId = userId;
        this.cardId = cardId;
        this.addDate = addDate;
        this.activityText = activityText;
    }

    public ActivityJsonPost() {
    }
}
