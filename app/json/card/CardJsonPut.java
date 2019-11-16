package json.card;

public class CardJsonPut extends CardJsonPost {

    private String userId;

    public CardJsonPut() {
    }

    public CardJsonPut(String title, String description, Integer labelId, Integer numberOnList, Integer listId, String userId) {
        super(title, description, labelId, numberOnList, listId);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
