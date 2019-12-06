package converters.attachment;

import json.attachment.CardAttachmentGet;
import models.Attachment;

import javax.inject.Singleton;
import java.util.function.Function;

@Singleton
public class AttachmentToCardAttachmentGet implements Function<Attachment, CardAttachmentGet> {
    @Override
    public CardAttachmentGet apply(Attachment attachment) {
        return new CardAttachmentGet(attachment.getAttachment(), attachment.getName(), attachment.getId());
    }
}
