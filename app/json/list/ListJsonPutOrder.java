package json.list;

public class ListJsonPutOrder{
    private Integer listId;
    private Integer numberOnBoard;

    public ListJsonPutOrder() {
    }

    public ListJsonPutOrder(Integer listId, Integer numberOnBoard) {
        this.listId = listId;
        this.numberOnBoard = numberOnBoard;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getNumberOnBoard() {
        return numberOnBoard;
    }

    public void setNumberOnBoard(Integer numberOnBoard) {
        this.numberOnBoard = numberOnBoard;
    }
}
