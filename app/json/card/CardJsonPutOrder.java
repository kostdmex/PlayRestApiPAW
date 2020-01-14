package json.card;

public class CardJsonPutOrder {
    private Integer cardId;
    private Integer numberOnList;

    public CardJsonPutOrder() {
    }

    public CardJsonPutOrder(Integer cardId, Integer numberOnList) {
        this.cardId = cardId;
        this.numberOnList = numberOnList;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getNumberOnList() {
        return numberOnList;
    }

    public void setNumberOnList(Integer numberOnList) {
        this.numberOnList = numberOnList;
    }
}
