package com.example.VoloConnect.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "volunteers")
public class Volunteer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(columnDefinition = "TEXT")
    private String skills;
    
    @Column(columnDefinition = "TEXT")
    private String availability;
    
    @Column(columnDefinition = "TEXT")
    private String bio;
    
    @Column(columnDefinition = "TEXT")
    private String preferences;
    
    @Column
    @Enumerated(EnumType.STRING)
    private VolunteerStatus status = VolunteerStatus.PENDING;
    
    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL)
    private Set<EventVolunteer> eventVolunteers = new HashSet<>();
    
    // Enum for status
    public enum VolunteerStatus {
        ACTIVE, INACTIVE, PENDING
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getSkills() {
        return skills;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    public String getAvailability() {
        return availability;
    }
    
    public void setAvailability(String availability) {
        this.availability = availability;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public String getPreferences() {
        return preferences;
    }
    
    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
    
    public VolunteerStatus getStatus() {
        return status;
    }
    
    public void setStatus(VolunteerStatus status) {
        this.status = status;
    }
    
    public Set<EventVolunteer> getEventVolunteers() {
        return eventVolunteers;
    }
    
    public void setEventVolunteers(Set<EventVolunteer> eventVolunteers) {
        this.eventVolunteers = eventVolunteers;
    }
}
