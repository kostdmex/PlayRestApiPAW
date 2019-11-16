package json.list;

public class ListJsonPut {
    private String name;
    private Integer numberOnBoard;

    public ListJsonPut(String name, Integer numberOnBoard) {
        this.name = name;
        this.numberOnBoard = numberOnBoard;
    }

    public ListJsonPut() {
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOnBoard() {
        return numberOnBoard;
    }
}
