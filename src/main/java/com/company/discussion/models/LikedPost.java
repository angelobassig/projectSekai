package com.company.discussion.models;

import javax.persistence.*;

@Entity
@Table(name="liked_posts")
public class LikedPost {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "liked_posts_seq")
    @SequenceGenerator(name = "liked_posts_seq", sequenceName = "sequence_liked_posts", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // Constructors
    public LikedPost() {
    }

    public LikedPost(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
