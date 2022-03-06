package com.company.discussion.models;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_seq")
    @SequenceGenerator(name = "posts_seq", sequenceName = "sequence_posts", allocationSize = 1)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private boolean isActive = true;

    @Column
    private String comment;

    @OneToOne
    @JoinColumn(name = "sender_user_id", referencedColumnName = "id")
    private User senderUser;

    @OneToOne
    @JoinColumn(name = "receiver_user_id", referencedColumnName = "id")
    private User receiverUser;

    /*
    @Column
    private Set<User> likes;
    */

    // Constructors
    public Post() {
    }

    public Post(String title, String content, boolean isActive, String comment) {
        this.title = title;
        this.content = content;
        this.isActive = isActive;
        this.comment = comment;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public User getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(User receiverUser) {
        this.receiverUser = receiverUser;
    }
}
