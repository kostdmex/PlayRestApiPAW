package json.list;

public class ListJsonPost {

    private String name;
    private Integer boardId;
    private Integer numberOnBoard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getNumberOnBoard() {
        return numberOnBoard;
    }

    public void setNumberOnBoard(Integer numberOnBoard) {
        this.numberOnBoard = numberOnBoard;
    }

    public ListJsonPost() {
    }

    public ListJsonPost(String name, Integer boardId, Integer numberOnBoard) {
        this.name = name;
        this.boardId = boardId;
        this.numberOnBoard = numberOnBoard;
    }

}
