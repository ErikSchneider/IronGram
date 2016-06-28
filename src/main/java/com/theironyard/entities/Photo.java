package com.theironyard.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Erik on 6/28/16.
 */
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User sender;

    @ManyToOne
    User recipient;

//    boolean isPrivate = false;

    @Column(nullable = false)
    String filename;

    LocalDateTime lDT;


    public Photo() {
    }

    public Photo(User sender, User recipient, String filename) {
        this.sender = sender;
        this.recipient = recipient;
        this.filename = filename;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

//    public boolean isPrivate() {
//        return isPrivate;
//    }
//
//    public void setPrivate(boolean aPrivate) {
//        isPrivate = aPrivate;
//    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LocalDateTime getlDT() {
        return lDT;
    }

    public void setlDT(LocalDateTime lDT) {
        this.lDT = lDT;
    }
}
