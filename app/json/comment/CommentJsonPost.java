package json.comment;

public class CommentJsonPost {

    private Integer userId;
    private Integer cardId;
    private String content;

    public CommentJsonPost(Integer userId, Integer cardId, String content) {
        this.userId = userId;
        this.cardId = cardId;
        this.content = content;
    }

    public CommentJsonPost() {
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
