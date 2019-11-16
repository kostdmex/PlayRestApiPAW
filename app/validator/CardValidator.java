package validator;

import json.card.CardJsonPost;
import json.card.CardJsonPut;
import repository.CardFinder;

import javax.inject.Singleton;

@Singleton
public class CardValidator {

    public static boolean validateCardPut(CardJsonPut cardJsonPut){
        if(cardJsonPut.getUserId() == null){
            return false;
        }
        return true;
    }

    public static boolean validateCardPost(CardJsonPost cardJsonPost){
        if(cardJsonPost.getTitle() == null || cardJsonPost.getListId() == null || cardJsonPost.getNumberOnList() == null){
            return false;
        }else return ListValidator.checkIfListExists(cardJsonPost.getListId());
    }

    public static boolean validateIdCrdExists(Integer cardId){
        return CardFinder.findCardById(cardId) != null;
    }

}
