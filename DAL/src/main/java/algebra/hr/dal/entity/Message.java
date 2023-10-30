package algebra.hr.dal.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MessageID")
    private int messageID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SenderUsername", nullable = false)
    private User senderUsername;  // Establishing a many-to-one relationship with User for sender

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReceiverUsername", nullable = false)
    private User receiverUsername;  // Establishing a many-to-one relationship with User for receiver

    @Column(name = "Content", nullable = false)
    private String content;

    @Column(name = "TimeStamp", nullable = false)
    private LocalDateTime timeStamp;

    public Message() {
    }

    public Message(User senderUsername, User receiverUsername, String content, LocalDateTime timeStamp) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public User getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(User senderUsername) {
        this.senderUsername = senderUsername;
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

    @Override
    public String toString() {
        return "Message{" +
                "messageID=" + messageID +
                ", senderUsername=" + senderUsername +
                ", receiverUsername=" + receiverUsername +
                ", content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
