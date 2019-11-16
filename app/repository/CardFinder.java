package repository;

import io.ebean.Finder;
import models.Card;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CardFinder {

    private static final Finder<Integer, Card> FINDER = new Finder<>(Card.class);

    public static List<Card> findAllByListId(Integer listId){
        return FINDER.query().where().eq("list_id", listId).findList();
    }

    public static Card findCardById(Integer cardId){
        return FINDER.query().where().eq("id", cardId).findOne();
    }
}
