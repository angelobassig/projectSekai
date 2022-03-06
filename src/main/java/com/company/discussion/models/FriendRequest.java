package com.company.discussion.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="friend_requests")
public class FriendRequest {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_requests_seq")
    @SequenceGenerator(name = "friend_requests_seq", sequenceName = "sequence_friend_requests", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;

    @OneToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private User receiver;

    // Constructors
    public FriendRequest() {
    }

    public FriendRequest(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
