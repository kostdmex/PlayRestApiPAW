package repository;

import io.ebean.Finder;
import models.Comment;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CommentFinder {

    private static final Finder<Integer, Comment> FINDER = new Finder<>(Comment.class);

    public static List<Comment> findByCardId(Integer cardId){
        return FINDER.query().where().eq("card_Id", cardId).findList();
    }

    public static Comment findById(Integer commentId){
        return FINDER.query().where().eq("id", commentId).findOne();
    }
}
