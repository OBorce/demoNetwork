package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "demo", name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private NetworkUser createdBy;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;


    public Post() {
    }

    public Post(String title, String description, LocalDateTime dateCreated, NetworkUser createdBy, Location location) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public NetworkUser getCreatedBy() {
        return createdBy;
    }

    public Location getLocation() {
        return location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", createdBy=" + createdBy +
                ", location=" + location +
                '}';
    }
}
