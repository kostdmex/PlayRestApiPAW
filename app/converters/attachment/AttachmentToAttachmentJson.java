package converters.attachment;

import json.attachment.CardAttachmentJson;
import models.Attachment;

import javax.inject.Singleton;
import java.util.function.Function;

@Singleton
public class AttachmentToAttachmentJson implements Function<Attachment, CardAttachmentJson> {
    @Override
    public CardAttachmentJson apply(Attachment attachment) {
        return new CardAttachmentJson(attachment.getAttachment(), attachment.getName(), attachment.getId());
    }
}
