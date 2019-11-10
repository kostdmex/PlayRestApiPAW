package json.board;

public class BoardJsonPut {

    public String name;
    public String background;

    public BoardJsonPut() {
    }

    public BoardJsonPut(String name, String background) {
        this.name = name;
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public String getBackground() {
        return background;
    }
}
