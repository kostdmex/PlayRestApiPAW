package validator;

import json.comment.CommentJsonPost;
import repository.CardFinder;
import repository.UserFinder;

import javax.inject.Singleton;

@Singleton
public class CommentValidator {

    public static boolean validateCommentPost(CommentJsonPost commentJsonPost){
        if(commentJsonPost.getCardId() == null || commentJsonPost.getUserId() == null || commentJsonPost.getContent() == null){
            return false;
        }

        if(UserFinder.findById(commentJsonPost.getUserId()) == null || CardFinder.findCardById(commentJsonPost.getCardId()) == null){
            return false;
        }

        return true;
    }

}
