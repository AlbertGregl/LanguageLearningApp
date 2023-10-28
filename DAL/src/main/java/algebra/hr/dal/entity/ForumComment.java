package algebra.hr.dal.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="ForumComment")
public class ForumComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ForumCommentID")
    private int forumCommentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ForumPostID", nullable = false)
    private ForumPost forumPost;  // Establishing a many-to-one relationship with ForumPost

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UsernamePoster", nullable = false)
    private User usernamePoster;  // Establishing a many-to-one relationship with User

    @Column(name = "Content", nullable = false)
    private String content;

    @Column(name = "TimeStamp", nullable = false)
    private LocalDateTime timeStamp;

    public ForumComment() {
    }

    public ForumComment(ForumPost forumPost, User usernamePoster, String content, LocalDateTime timeStamp) {
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

    public ForumPost getForumPost() {
        return forumPost;
    }

    public void setForumPost(ForumPost forumPost) {
        this.forumPost = forumPost;
    }

    public User getUsernamePoster() {
        return usernamePoster;
    }

    public void setUsernamePoster(User usernamePoster) {
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
        return "ForumComment{" +
                "forumCommentID=" + forumCommentID +
                ", forumPost=" + forumPost +
                ", usernamePoster=" + usernamePoster +
                ", content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}