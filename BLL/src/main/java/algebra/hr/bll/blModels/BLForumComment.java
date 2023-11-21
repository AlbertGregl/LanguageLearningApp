package algebra.hr.bll.blModels;

import java.time.LocalDateTime;

public class BLForumComment {
    private int forumCommentID;
    private int forumPost;
    private String usernamePoster;
    //@NotEmpty(message = "Content is required")
    private String content;
    private LocalDateTime timeStamp;

    public BLForumComment() {
    }

    public BLForumComment(int forumPost, String usernamePoster, String content, LocalDateTime timeStamp) {
        this.forumPost = forumPost;
        this.usernamePoster = usernamePoster;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public int getForumCommentID() {
        return forumCommentID;
    }

    public void setForumCommentID(int forumCommentID) {
        this.forumCommentID = forumCommentID;
    }

    public int getForumPost() {
        return forumPost;
    }

    public void setForumPost(int forumPost) {
        this.forumPost = forumPost;
    }

    public String getUsernamePoster() {
        return usernamePoster;
    }

    public void setUsernamePoster(String usernamePoster) {
        this.usernamePoster = usernamePoster;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "BLForumComment{" +
                "forumCommentID=" + forumCommentID +
                ", forumPost=" + forumPost +
                ", usernamePoster='" + usernamePoster + '\'' +
                ", content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
