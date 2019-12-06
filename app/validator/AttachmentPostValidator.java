package validator;

import json.attachment.CardAttachment;

public class AttachmentPostValidator {

    public static boolean validateAttachmentPost(CardAttachment attachment){
        return attachment.getName() != null && attachment.getAttachment() != null;
    }

}
