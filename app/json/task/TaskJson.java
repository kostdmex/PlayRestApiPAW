package json.task;

public class TaskJson {
    private Integer id;
    private String name;
    private boolean isDone;
    private Integer taskListId;

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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Integer getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Integer taskListId) {
        this.taskListId = taskListId;
    }

    public TaskJson(Integer id, String name, boolean isDone, Integer taskListId) {
        this.id = id;
        this.name = name;
        this.isDone = isDone;
        this.taskListId = taskListId;
    }

    public TaskJson() {
    }
}
