package service;

import converters.card.CardJsonPostToCard;
import converters.card.CardToCardJson;
import io.ebean.Model;
import json.card.CardJson;
import json.card.CardJsonPost;
import json.card.CardJsonPut;
import json.card.CardJsonPutOrder;
import models.Card;
import play.db.ebean.Transactional;
import repository.CardFinder;
import repository.ListFinder;
import validator.CardValidator;
import validator.ListValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CardService {

    @Inject
    private CardToCardJson cardToCardJson;
    @Inject
    private CardJsonPostToCard cardJsonPostToCard;
    @Inject
    private AuthService authService;

    public List<CardJson> getCardsByListId(Integer listId) {
        List<Card> cards = CardFinder.findAllByListId(listId);

        if (cards.isEmpty()) {
            return null;
        }

        return cards.stream().map(cardToCardJson).sorted(Comparator.comparingInt(CardJson::getNumberOnList)).collect(Collectors.toList());
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
		
        List<CardJson> cardsOnList = getCardsByListId(cardJsonPost.getListId());
		
		if(!cardsOnList == null){
		if(!cardsOnList.isEmpty()){
        boolean isNumberTaken = false;
        for (CardJson cardJson : cardsOnList) {
            if(cardJson.getNumberOnList() == cardJsonPost.getNumberOnList())
                isNumberTaken = true;
        }

        if(isNumberTaken){
            cardJsonPost.setNumberOnList(cardsOnList.get(cardsOnList.size() - 1).getNumberOnList() + 1);
        }
		}
		}
        Card card = cardJsonPostToCard.apply(cardJsonPost);
        if(card.getAddDate() == null){
            card.setAddDate(LocalDateTime.now());
        }

        card.save();

        return card.getId();
    }

    public boolean updateCard(Integer cardId, CardJsonPut cardJsonPut, Integer userId) {
        if(!CardValidator.validateIdCrdExists(cardId)){
            return false;
        }

//        if(!CardValidator.validateCardPut(cardJsonPut)){
//            return false;
//        }

        if(authService.validateUserPermissionToCard(cardId, userId)){
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

    @Transactional
    public boolean setCardOrder(Integer listId, List<CardJsonPutOrder> cards) {
        if(cards.stream().anyMatch(i -> Collections.frequency(cards, i.getNumberOnList()) > 1)){
            return false;
        }
        List<Card> cardsOnList = CardFinder.findAllByListId(listId);

        if(cardsOnList.size() != cards.size()){
            return false;
        }

        for (CardJsonPutOrder card : cards) {
            if(cardsOnList.stream().noneMatch(li -> li.getId().equals(card.getCardId()))){
                return false;
            }
        }

//        boolean isNeedToChange = false;
//
//        for (Card card : cardsOnList) {
//            if(card.getNumberOnList().equals(cards.stream().filter(li -> !li.getCardId().equals(card.getId())).findFirst().get().getNumberOnList())){
//                isNeedToChange = true;
//            }
//        }

//        if(isNeedToChange) {
            System.out.println("changing");
            cardsOnList.forEach(card -> card.setNumberOnList(null));
            cardsOnList.forEach(Model::save);
            cardsOnList.forEach(card -> card.setNumberOnList(cards.stream().filter(cardPut -> cardPut.getCardId().equals(card.getId())).findFirst().get().getNumberOnList()));
            cardsOnList.forEach(Model::save);
//        }
        return true;

    }
}
