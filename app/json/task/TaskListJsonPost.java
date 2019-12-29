package json.task;

public class TaskListJsonPost {
    private String name;
    private Integer cardId;

    public TaskListJsonPost(String name, Integer cardId) {
        this.name = name;
        this.cardId = cardId;
    }

    public TaskListJsonPost() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}
