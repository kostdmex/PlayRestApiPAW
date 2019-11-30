package converters.comment;

import json.comment.CommentJsonPost;
import models.Card;
import models.Comment;
import models.User;
import repository.CardFinder;
import repository.UserFinder;

import javax.inject.Singleton;
import java.sql.Timestamp;
import java.util.function.Function;

@Singleton
public class CommentJsonPostToComment implements Function<CommentJsonPost, Comment> {

    @Override
    public Comment apply(CommentJsonPost commentJsonPost) {
        User user = UserFinder.findById(commentJsonPost.getUserId());
        Card card = CardFinder.findCardById(commentJsonPost.getCardId());
        return new Comment(new Timestamp(System.currentTimeMillis()), commentJsonPost.getContent(), user, card);
    }
}
