package service;

import converters.attachment.AttachmentToAttachmentJson;
import converters.attachment.AttachmentToCardAttachmentGet;
import json.attachment.CardAttachmentJson;
import json.attachment.CardAttachment;
import json.attachment.CardAttachmentGet;
import models.Attachment;
import repository.AttachmentFinder;
import repository.CardFinder;
import repository.CommentFinder;
import validator.AttachmentPostValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class AttachmentService {

    @Inject
    private AttachmentToCardAttachmentGet attachmentToCardAttachmentGet;
    @Inject
    private AttachmentToAttachmentJson attachmentToAttachmentJson;

    public Integer addAttachmentToCard(Integer cardId, CardAttachment cardAttachment){
        if(CardFinder.findCardById(cardId) == null || !AttachmentPostValidator.validateAttachmentPost(cardAttachment)){
            return -1;
        }

        Attachment attachment = new Attachment();
        attachment.setCard_id(cardId);
        attachment.setName(cardAttachment.getName());
        attachment.setAttachment(cardAttachment.getAttachment());
        attachment.save();
        return attachment.getId();
    }

    public List<CardAttachmentGet> getCardAttachment(Integer cardId){
        if(CardFinder.findCardById(cardId) == null){
            return null;
        }

        return AttachmentFinder.findByCardId(cardId)
                .stream()
                .map(attachmentToCardAttachmentGet)
                .collect(Collectors.toList());
    }

    public CardAttachmentJson getAttachmentById(Integer attachmentId){
        if(AttachmentFinder.findById(attachmentId) == null){
            return null;
        }

        return attachmentToAttachmentJson.apply(AttachmentFinder.findById(attachmentId));
    }

    public void deleteAttachmentById(Integer attachmentId) {
        Attachment attachment = AttachmentFinder.findById(attachmentId);
        if(attachment != null){
            attachment.deletePermanent();
        }
    }

    public Integer addAttachmentToComment(Integer commentId, CardAttachment cardAttachment) {
        if(!AttachmentPostValidator.validateAttachmentPost(cardAttachment)){
            return -1;
        }

        Attachment attachment = new Attachment();
        attachment.setName(cardAttachment.getName());
        attachment.setAttachment(cardAttachment.getAttachment());
        attachment.setComment_id(commentId);
        attachment.setCard_id(CommentFinder.findById(commentId).getCard().getId());
        attachment.save();

        return attachment.getId();
    }
}
