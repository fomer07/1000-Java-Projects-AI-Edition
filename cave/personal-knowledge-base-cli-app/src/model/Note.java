package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Note implements Serializable {
    private final String id;
    private String title;
    private String content;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Note(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setContent(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }
    @Override
    public String toString() {
        return "Note ID: " + id +
                "\nTitle: " + title +
                "\nCreated: " + createdAt +
                "\nUpdated: " + updatedAt +
                "\nContent:\n" + content;
    }
}
