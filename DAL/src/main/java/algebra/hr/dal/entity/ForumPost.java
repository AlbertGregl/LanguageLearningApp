package algebra.hr.dal.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="ForumPost")
public class ForumPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ForumPostID")
    private int forumPostID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReceiverUsername", nullable = false)
    private User receiver;

    @Column(name = "Content", nullable = false)
    private String content;

    @Column(name = "TimeStamp", nullable = false)
    private LocalDateTime timeStamp;

    public ForumPost() {
    }

    public ForumPost(User receiver, String content, LocalDateTime timeStamp) {
        this.receiver = receiver;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public int getForumPostID() {
        return forumPostID;
    }

    public void setForumPostID(int forumPostID) {
        this.forumPostID = forumPostID;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
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
        return "ForumPost{" +
                "forumPostID=" + forumPostID +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
