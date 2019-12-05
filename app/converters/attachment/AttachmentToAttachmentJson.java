package converters.attachment;

import json.attachment.AttachmentJson;
import models.Attachment;

import javax.inject.Singleton;
import java.util.function.Function;

@Singleton
public class AttachmentToAttachmentJson implements Function<Attachment, AttachmentJson> {
    @Override
    public AttachmentJson apply(Attachment attachment) {
        return new AttachmentJson(attachment.getAttachment(), attachment.getName(), attachment.getId());
    }
}
