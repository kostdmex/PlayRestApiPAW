package json.comment;

import json.attachment.CardAttachmentGet;

import java.sql.Timestamp;
import java.util.List;

public class CommentJson {
    private Integer id;
    private String content;
    private Timestamp addDate;
    private String userName;
    private String userSurname;
    private Integer userId;
    private List<CardAttachmentGet> attachments;

    public List<CardAttachmentGet> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<CardAttachmentGet> attachments) {
        this.attachments = attachments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CommentJson(Integer id, String content, Timestamp addDate, String userName, String userSurname, Integer userId, List<CardAttachmentGet> attachments) {
        this.id = id;
        this.content = content;
        this.addDate = addDate;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userId = userId;
        this.attachments = attachments;
    }

    public CommentJson() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
