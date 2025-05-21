package com.example.VoloConnect.repository;

import com.example.VoloConnect.models.Event;
import com.example.VoloConnect.models.EventVolunteer;
import com.example.VoloConnect.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventVolunteerRepository extends JpaRepository<EventVolunteer, Long> {
    
    // Find all event-volunteer mappings for a specific event
    List<EventVolunteer> findByEvent(Event event);
    
    // Find all event-volunteer mappings for a specific volunteer
    List<EventVolunteer> findByVolunteer(Volunteer volunteer);
    
    // Find a specific event-volunteer mapping by event and volunteer
    Optional<EventVolunteer> findByEventAndVolunteer(Event event, Volunteer volunteer);
    
    // Find all event-volunteer mappings by role
    List<EventVolunteer> findByRoleIgnoreCase(String role);
    
    // Find by event ID and volunteer ID
    Optional<EventVolunteer> findByEvent_IdAndVolunteer_Id(Long eventId, Long volunteerId);
    
    // Count volunteers for a specific event
    long countByEvent(Event event);
    
    // Count events for a specific volunteer
    long countByVolunteer(Volunteer volunteer);
    
    // Delete all mappings for a specific event
    void deleteByEvent(Event event);
    
    // Delete all mappings for a specific volunteer
    void deleteByVolunteer(Volunteer volunteer);
}
