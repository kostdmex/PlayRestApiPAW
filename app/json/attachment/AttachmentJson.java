package json.attachment;

public class AttachmentJson extends CardAttachmentGet {
    public AttachmentJson(String attachment, String name, Integer id) {
        super(attachment, name, id);
    }

    public AttachmentJson(String attachment, Integer id) {
        super(attachment, id);
    }

    public AttachmentJson(Integer id) {
        super(id);
    }

    public AttachmentJson() {
    }
}
