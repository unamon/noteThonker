package com.notethonker.notethonker.domain;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty
    private String title;
    @Column
    private String content;
    @Column
    private LocalDateTime date_created;
    @Column
    private LocalDateTime date_alarm;

    public Note() {
    }
    public Note(String title, String content, LocalDateTime date_created, LocalDateTime date_alarm) {
        this.title = title;
        this.content = content;
        this.date_created = date_created;
        this.date_alarm = date_alarm;
    }
    public Note(Note note) { 
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
        this.date_created = note.getDate_created();
        this.date_alarm = note.getDate_alarm();
    }
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
    public LocalDateTime getDate_created() {
        return date_created;
    }
    public void setDate_created(LocalDateTime date_created) {
        this.date_created = date_created;
    }
    public LocalDateTime getDate_alarm() {
        return date_alarm;
    }
    public void setDate_alarm(LocalDateTime date_alarm) {
        this.date_alarm = date_alarm;
    }    

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals( obj,this);
    }
}
