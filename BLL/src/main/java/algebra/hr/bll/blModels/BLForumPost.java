package algebra.hr.bll.blModels;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.Set;

public class BLForumPost {
    private int forumPostID;
    private Set<String> receivers;
    @NotEmpty(message = "Content is required")
    private String content;
    private LocalDateTime timeStamp;

    public BLForumPost() {
    }

    public BLForumPost(Set<String> receivers, String content, LocalDateTime timeStamp) {
        this.receivers = receivers;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public int getForumPostID() {
        return forumPostID;
    }

    public void setForumPostID(int forumPostID) {
        this.forumPostID = forumPostID;
    }

    public Set<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<String> receivers) {
        this.receivers = receivers;
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
        return "BLForumPost{" +
                "forumPostID=" + forumPostID +
                ", receivers=" + receivers +
                ", content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
