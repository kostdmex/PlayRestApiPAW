package repository;

import io.ebean.Finder;
import models.Attachment;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AttachmentFinder {

    private static final Finder<Integer, Attachment> FINDER = new Finder<>(Attachment.class);

    public static List<Attachment> findByCardId(Integer cardId){
        return FINDER.query().where().eq("card_id", cardId).findList();
    }

    public static Attachment findById(Integer attachmentId){
        return FINDER.query().where().eq("id", attachmentId).findOne();
    }

    public static List<Attachment> findByCommentId(Integer commentId){
        return FINDER.query().where().eq("comment_id", commentId).findList();
    }

}
