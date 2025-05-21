package com.example.VoloConnect.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;
    
    @Column(nullable = false)
    private String location;
    
    @Column(nullable = false)
    private Integer capacity;
    
    @Column
    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.UPCOMING;
    
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<EventVolunteer> eventVolunteers = new HashSet<>();
    
    // Enum for status
    public enum EventStatus {
        UPCOMING, ONGOING, COMPLETED, CANCELLED
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public Integer getCapacity() {
        return capacity;
    }
    
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    
    public EventStatus getStatus() {
        return status;
    }
    
    public void setStatus(EventStatus status) {
        this.status = status;
    }
    
    public Set<EventVolunteer> getEventVolunteers() {
        return eventVolunteers;
    }
    
    public void setEventVolunteers(Set<EventVolunteer> eventVolunteers) {
        this.eventVolunteers = eventVolunteers;
    }
}
