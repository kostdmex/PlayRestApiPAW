package service;

import converters.card.CardJsonPostToCard;
import converters.card.CardToCardJson;
import json.card.CardJson;
import json.card.CardJsonPost;
import json.card.CardJsonPut;
import models.Card;
import repository.CardFinder;
import repository.ListFinder;
import validator.CardValidator;
import validator.ListValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CardService {

    @Inject
    private CardToCardJson cardToCardJson;
    @Inject
    private CardJsonPostToCard cardJsonPostToCard;

    public List<CardJson> getCardsByListId(Integer listId) {
        List<Card> cards = CardFinder.findAllByListId(listId);

        if (cards.isEmpty()) {
            return null;
        }

        return cards.stream().map(cardToCardJson::apply).collect(Collectors.toList());
    }

    public CardJson getCardById(Integer cardId) {
        Card card = CardFinder.findCardById(cardId);
        if (card == null) {
            return null;
        }

        return cardToCardJson.apply(card);
    }

    public Integer createCard(CardJsonPost cardJsonPost) {
        if (!CardValidator.validateCardPost(cardJsonPost)) {
            return null;
        }

        Card card = cardJsonPostToCard.apply(cardJsonPost);

        card.save();

        return card.getId();
    }

    public boolean updateCard(Integer cardId, CardJsonPut cardJsonPut) {
        if(!CardValidator.validateIdCrdExists(cardId)){
            return false;
        }

        if(!CardValidator.validateCardPut(cardJsonPut)){
            return false;
        }

        Card cardToUpdate = CardFinder.findCardById(cardId);

        if(cardJsonPut.getDescription() != null){
            cardToUpdate.setDescription(cardJsonPut.getDescription());
        }
        if(cardJsonPut.getLabelId() != null){
            cardToUpdate.setLabelId(cardJsonPut.getLabelId());
        }
        if(cardJsonPut.getListId() != null){
            if(!ListValidator.checkIfListExists(cardJsonPut.getListId())){
                return false;
            }
            models.List list = ListFinder.findListById(cardJsonPut.getListId());

            cardToUpdate.setList(list);
        }
        if(cardJsonPut.getNumberOnList() != null){
            cardToUpdate.setNumberOnList(cardJsonPut.getNumberOnList());
        }
        if(cardJsonPut.getTitle() != null){
            cardToUpdate.setTitle(cardJsonPut.getTitle());
        }

        cardToUpdate.save();

        return true;
    }
}
