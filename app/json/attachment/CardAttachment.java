package json.attachment;

public class CardAttachment {

    private String attachment;
    private String name;

    public CardAttachment(String attachment, String name) {
        this.attachment = attachment;
        this.name = name;
    }

    public CardAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardAttachment() {
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
