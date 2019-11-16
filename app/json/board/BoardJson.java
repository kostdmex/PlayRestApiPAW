package json.board;

import json.list.ListJson;

import java.util.List;

public class BoardJson {

    public Integer id;
    public String name;
    public Integer team_id;
    public String background;
    public List<ListJson> lists;

    public BoardJson() {

    }

    public BoardJson(Integer id, String name,Integer team_id, String background, List<ListJson> lists) {
        this.id = id;
        this.name = name;
        this.team_id = team_id;
        this.background = background;
        this.lists = lists;
    }

}
