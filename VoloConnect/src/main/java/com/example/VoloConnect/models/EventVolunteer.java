package com.example.VoloConnect.models;

import jakarta.persistence.*;

@Entity
@Table(name = "event_volunteers")
public class EventVolunteer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    
    @ManyToOne
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;
    
    @Column(nullable = false)
    private String role;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Event getEvent() {
        return event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    public Volunteer getVolunteer() {
        return volunteer;
    }
    
    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
