package com.anma.conflappcore.models.dto;

public class SpaceDto {
    private long id;
    private String title;
    private String category;
    private String key;
    private long authorId;

    public SpaceDto() { }

    public SpaceDto(long id, String title, String category, String key, long authorId) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.key = key;
        this.authorId = authorId;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}
