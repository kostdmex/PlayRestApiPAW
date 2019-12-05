package service;

import converters.attachment.AttachmentToAttachmentJson;
import converters.attachment.AttachmentToCardAttachmentGet;
import json.attachment.AttachmentJson;
import json.attachment.CardAttachment;
import json.attachment.CardAttachmentGet;
import models.Attachment;
import repository.AttachmentFinder;
import repository.CardFinder;

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

    public Integer addAttachmentToCard(Integer cardId, CardAttachment attachmentPost){
        if(CardFinder.findCardById(cardId) == null || attachmentPost.getAttachment() == null || attachmentPost.getName() == null){
            return -1;
        }

        Attachment attachment = new Attachment();
        attachment.setCard_id(cardId);
        attachment.setName(attachmentPost.getName());
        attachment.setAttachment(attachmentPost.getAttachment());
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

    public AttachmentJson getAttachmentById(Integer attachmentId){
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
}
