package json.task;

import java.util.List;

public class TaskListJson {

    private Integer id;
    private String name;
    private Integer cardId;
    private List<TaskJson> taskJsons;

    public TaskListJson(Integer id, String name, Integer cardId, List<TaskJson> taskJsons) {
        this.id = id;
        this.name = name;
        this.cardId = cardId;
        this.taskJsons = taskJsons;
    }

    public TaskListJson() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<TaskJson> getTaskJsons() {
        return taskJsons;
    }

    public void setTaskJsons(List<TaskJson> taskJsons) {
        this.taskJsons = taskJsons;
    }
}
