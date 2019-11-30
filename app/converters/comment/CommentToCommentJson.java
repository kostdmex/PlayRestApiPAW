package converters.comment;

import json.comment.CommentJson;
import models.Comment;

import javax.inject.Singleton;
import java.util.function.Function;

@Singleton
public class CommentToCommentJson implements Function<Comment, CommentJson> {
    @Override
    public CommentJson apply(Comment comment) {
        return new CommentJson(comment.getCommentContent(), comment.getAddDate(), comment.getUser().getName(), comment.getUser().getSurname(), comment.getUser().getId());
    }
}
