package com.company.discussion.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="friends")
public class Friend {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friends_seq")
    @SequenceGenerator(name = "friends_seq", sequenceName = "sequence_friends", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "id")
    private User requester;

    @OneToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private User recipient;

    // Constructors
    public Friend() {
    }

    public Friend(User requester, User recipient) {
        this.requester = requester;
        this.recipient = recipient;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
