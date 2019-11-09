package json;

public class BoardJson {

    public Integer id;
    public String name;
    public Integer team_id;
    public String background;

    public BoardJson() {

    }

    public BoardJson(Integer id, String name,Integer team_id, String background) {
        this.id = id;
        this.name = name;
        this.team_id = team_id;
        this.background = background;
    }

}
