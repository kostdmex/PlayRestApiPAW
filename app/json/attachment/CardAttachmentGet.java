package json.attachment;

public class CardAttachmentGet extends CardAttachment{

    private Integer id;

    public CardAttachmentGet(String attachment, String name, Integer id) {
        super(attachment, name);
        this.id = id;
    }

    public CardAttachmentGet(String attachment, Integer id) {
        super(attachment);
        this.id = id;
    }

    public CardAttachmentGet(Integer id) {
        this.id = id;
    }

    public CardAttachmentGet() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
