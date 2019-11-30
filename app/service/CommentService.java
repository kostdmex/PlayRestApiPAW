package service;

import converters.comment.CommentJsonPostToComment;
import converters.comment.CommentToCommentJson;
import json.comment.CommentJson;
import json.comment.CommentJsonPost;
import models.Comment;
import repository.CommentFinder;
import validator.CommentValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CommentService {

    @Inject
    private CommentJsonPostToComment commentJsonPostToComment;
    @Inject
    private CommentToCommentJson commentToCommentJson;

    public Integer addCommentToCard(CommentJsonPost commentJsonPost){
        if(!CommentValidator.validateCommentPost(commentJsonPost)){
            return -1;
        }

        Comment comment = commentJsonPostToComment.apply(commentJsonPost);
        comment.save();
        return comment.getId();
    }

    public List<CommentJson> getCommentsByCardId(Integer cardId){
        List<Comment> comments = CommentFinder.findByCardId(cardId);
        if(comments.isEmpty()){
            return null;
        }

        return comments.stream().map(commentToCommentJson).collect(Collectors.toList());
    }
}
