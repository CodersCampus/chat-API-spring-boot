package com.coderscampus.chatapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Document(collection = "messages")
public class Message {

    @Id
    @JsonIgnore
    private String id;

    private UUID uniqueId;
    private String sender; // You can use ObjectId type here if needed
    private String recipient; // You can use ObjectId type here if needed
    private String message;
    private Date createdAt;

    public Message(String sender, String recipient, String message, Date createdAt, UUID uniqueId) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.createdAt = createdAt;
        this.uniqueId = uniqueId;
    }
    public Message () {}

    public UUID getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Message{" +

                ", uniqueId=" + uniqueId +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
