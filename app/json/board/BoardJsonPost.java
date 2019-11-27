package json.board;

public class BoardJsonPost {

    public String name;
    public Integer team_id;
    public String background;
    public boolean isPublic;


    public BoardJsonPost(String name, Integer team_id, String background, boolean isPublic) {
        this.name = name;
        this.team_id = team_id;
        this.background = background;
        this.isPublic = isPublic;
    }

    public BoardJsonPost() {
    }

    public String getName() {
        return name;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public String getBackground() {
        return background;
    }

    public Boolean getPublic() {
        return isPublic;
    }
}
