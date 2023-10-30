package algebra.hr.dal.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID")
    private int notificationID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReceiverUsername", nullable = false)
    private User receiverUsername;  // Establishing a many-to-one relationship with User for receiver

    @Column(name = "Content", nullable = false)
    private String content;

    @Column(name = "TimeStamp", nullable = false)
    private LocalDateTime timeStamp;

    @Column(name = "IsSent", nullable = false)
    private boolean isSent;

    public Notification() {
    }

    public Notification(User receiverUsername, String content, LocalDateTime timeStamp, boolean isSent) {
        this.receiverUsername = receiverUsername;
        this.content = content;
        this.timeStamp = timeStamp;
        this.isSent = isSent;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public User getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(User receiverUsername) {
        this.receiverUsername = receiverUsername;
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

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationID=" + notificationID +
                ", receiverUsername=" + receiverUsername +
                ", content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                ", isSent=" + isSent +
                '}';
    }
}
