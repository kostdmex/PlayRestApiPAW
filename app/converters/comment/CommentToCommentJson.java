package converters.comment;

import converters.attachment.AttachmentToCardAttachmentGet;
import json.attachment.CardAttachmentGet;
import json.comment.CommentJson;
import models.Attachment;
import models.Comment;
import repository.AttachmentFinder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Singleton
public class CommentToCommentJson implements Function<Comment, CommentJson> {

    @Inject
    private AttachmentToCardAttachmentGet attachmentToCardAttachmentGet;

    @Override
    public CommentJson apply(Comment comment) {
        List<Attachment> attachmentList = AttachmentFinder.findByCommentId(comment.getId());
        List<CardAttachmentGet> attachments = new ArrayList<>();
        if(!attachmentList.isEmpty()) {
            attachments = attachmentList.stream().map(attachmentToCardAttachmentGet).collect(Collectors.toList());
        }
        return new CommentJson(comment.getId(),comment.getCommentContent(), comment.getAddDate(), comment.getUser().getName(), comment.getUser().getSurname(), comment.getUser().getId(), attachments);
    }
}
