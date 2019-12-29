package json.task;

public class TaskJsonPut {
    private String name;
    private Boolean done;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public TaskJsonPut(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public TaskJsonPut() {
    }
}
