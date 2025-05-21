package com.example.VoloConnect.services;

import com.example.VoloConnect.models.Event;
import com.example.VoloConnect.models.EventVolunteer;
import com.example.VoloConnect.models.Volunteer;

import java.util.List;

public interface EventManagementService {
    
    /**
     * Assign a volunteer to an event with a specific role
     * 
     * @param eventId the ID of the event
     * @param volunteerId the ID of the volunteer
     * @param role the role of the volunteer in the event
     * @return the ID of the created event-volunteer association
     */
    Long assignVolunteer(Long eventId, Long volunteerId, String role);
    
    /**
     * Remove a volunteer from an event
     * 
     * @param eventId the ID of the event
     * @param volunteerId the ID of the volunteer
     * @return true if the volunteer was successfully removed, false otherwise
     */
    boolean removeVolunteer(Long eventId, Long volunteerId);
    
    /**
     * Get all volunteers assigned to an event
     * 
     * @param eventId the ID of the event
     * @return list of volunteers with their roles
     */
    List<Volunteer> getEventVolunteers(Long eventId);
    
    /**
     * Get all events a volunteer is assigned to
     * 
     * @param volunteerId the ID of the volunteer
     * @return list of events with the volunteer's role
     */
    List<Event> getVolunteerEvents(Long volunteerId);
    
    /**
     * Update a volunteer's role in an event
     * 
     * @param eventId the ID of the event
     * @param volunteerId the ID of the volunteer
     * @param newRole the new role for the volunteer
     * @return true if the role was successfully updated, false otherwise
     */
    boolean updateVolunteerRole(Long eventId, Long volunteerId, String newRole);
}
