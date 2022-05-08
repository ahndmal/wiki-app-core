package com.anma.conflappcore.models.db;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String body;
    @Column(name = "parent_id")
    private long parentId;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "lst_edited")
    private LocalDateTime lastEdited;
    @Column(name = "user_id")
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
