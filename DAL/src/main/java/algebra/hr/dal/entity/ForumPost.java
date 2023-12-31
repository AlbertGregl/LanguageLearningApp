package algebra.hr.dal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="ForumPost")
public class ForumPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ForumPostID")
    private int forumPostID;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "forum_post_receivers",
            joinColumns = @JoinColumn(name = "forum_post_id"),
            inverseJoinColumns = @JoinColumn(name = "receiver_username")
    )
    private Set<User> receivers;

    @Column(name = "Content", nullable = false)
    @NotEmpty(message = "Content is required")
    private String content;

    @Column(name = "TimeStamp", nullable = false)
    private LocalDateTime timeStamp;

    public ForumPost() {
    }

    public ForumPost(Set<User> receivers, String content, LocalDateTime timeStamp) {
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

    public Set<User> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<User> receivers) {
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
        return "ForumPost{" +
                "forumPostID=" + forumPostID +
                ", receivers=" + receivers +
                ", content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
