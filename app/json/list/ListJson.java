package json.list;

public class ListJson {

    public Integer id;
    public String name;
    public Integer boardId;
    public Integer numberOnBoard;

    public ListJson(){

    }

    public ListJson(Integer id, String name, Integer boardId, Integer numberOnBoard) {
        this.id = id;
        this.name = name;
        this.boardId = boardId;
        this.numberOnBoard = numberOnBoard;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public Integer getNumberOnBoard() {
        return numberOnBoard;
    }
}
