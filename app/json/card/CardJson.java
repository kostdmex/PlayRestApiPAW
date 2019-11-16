package json.card;

public class CardJson {

    private Integer id;
    private String title;
    private String description;
    private Integer labelId;
    private Integer numberOnList;
    private Integer listId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getNumberOnList() {
        return numberOnList;
    }

    public void setNumberOnList(Integer numberOnList) {
        this.numberOnList = numberOnList;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public CardJson() {
    }

    public CardJson(Integer id, String title, String description, Integer labelId, Integer numberOnList, Integer listId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.labelId = labelId;
        this.numberOnList = numberOnList;
        this.listId = listId;
    }
}
