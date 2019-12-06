package json.attachment;

public class CardAttachmentJson extends CardAttachmentGet {
    public CardAttachmentJson(String attachment, String name, Integer id) {
        super(attachment, name, id);
    }

    public CardAttachmentJson(String attachment, Integer id) {
        super(attachment, id);
    }

    public CardAttachmentJson(Integer id) {
        super(id);
    }

    public CardAttachmentJson() {
    }
}
