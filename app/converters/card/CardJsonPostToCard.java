package converters.card;

import json.card.CardJsonPost;
import models.Card;
import models.List;
import repository.ListFinder;

import javax.inject.Singleton;
import java.util.function.Function;

@Singleton
public class CardJsonPostToCard implements Function<CardJsonPost, Card> {
    @Override
    public Card apply(CardJsonPost cardJsonPost) {
        List list = ListFinder.findListById(cardJsonPost.getListId());
        return new Card(cardJsonPost.getTitle(), cardJsonPost.getDescription(), cardJsonPost.getLabelId(), cardJsonPost.getNumberOnList(), null, list);
    }
}
