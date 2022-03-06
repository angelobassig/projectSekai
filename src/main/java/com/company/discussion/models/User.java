package com.company.discussion.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "sequence_users", allocationSize = 1)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String birthday;

    @Column
    private String gender;

    @Column
    private String mobileNumber;

    @Column
    private boolean isOnline = false;

    // OneToMany relationship between User Model and Album Model
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Album> albums;

//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private Set<Post> posts;

    // private Set<User> friends
    // private Set<User> friendRequests

    // Constructors
    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String birthday, String gender, String mobileNumber, boolean isOnline) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.isOnline = isOnline;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

//    public Set<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(Set<Post> posts) {
//        this.posts = posts;
//    }
}
