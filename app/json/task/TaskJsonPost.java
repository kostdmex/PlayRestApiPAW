package json.task;

public class TaskJsonPost {
    private String name;
    private Integer taskListId;

    public TaskJsonPost(String name, Integer taskListId) {
        this.name = name;
        this.taskListId = taskListId;
    }

    public TaskJsonPost() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Integer taskListId) {
        this.taskListId = taskListId;
    }
}
