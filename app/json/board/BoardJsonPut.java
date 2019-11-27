package json.board;

public class BoardJsonPut {

    public String name;
    public String background;
    public Boolean isPublic;

    public BoardJsonPut() {
    }

    public BoardJsonPut(String name, String background, Boolean isPublic) {
        this.name = name;
        this.background = background;
        this.isPublic = isPublic;
    }

    public String getName() {
        return name;
    }

    public String getBackground() {
        return background;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }
}
