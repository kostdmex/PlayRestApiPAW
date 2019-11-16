package converters.card;

import json.card.CardJson;
import models.Card;

import javax.inject.Singleton;
import java.util.function.Function;

@Singleton
public class CardToCardJson implements Function<Card, CardJson> {

    @Override
    public CardJson apply(Card card) {
        return new CardJson(card.getId(), card.getTitle(), card.getDescription(), card.getLabelId(), card.getNumberOnList(), card.getList().getId());
    }
}
