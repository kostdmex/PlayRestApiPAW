package json;

public class BoardJsonPost {

    public String name;
    public Integer team_id;
    public String background;


    public BoardJsonPost(String name, Integer team_id, String background) {
        this.name = name;
        this.team_id = team_id;
        this.background = background;
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
}
