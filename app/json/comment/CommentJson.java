package json.comment;

import java.sql.Timestamp;

public class CommentJson {
    private String content;
    private Timestamp addDate;
    private String userName;
    private String userSurname;
    private Integer userId;

    public CommentJson(String content, Timestamp addDate, String userName, String userSurname, Integer userId) {
        this.content = content;
        this.addDate = addDate;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userId = userId;
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
