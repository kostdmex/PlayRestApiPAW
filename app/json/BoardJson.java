package json;

public class BoardJson {

    public Integer id;
    public String name;
    public Integer team_id;
    public Integer background;

    public BoardJson() {

    }

    public BoardJson(Integer id, String name,Integer team_id, Integer background) {
        this.id = id;
        this.name = name;
        this.team_id = team_id;
        this.background = background;
    }

}
