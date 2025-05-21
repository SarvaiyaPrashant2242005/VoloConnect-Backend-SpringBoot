package com.example.VoloConnect.services;

import java.util.List;
import java.util.Map;

import com.example.VoloConnect.models.Volunteer;

public interface VolunteerManagementService {
    
    /**
     * Update volunteer profile data
     * 
     * @param volunteerId the ID of the volunteer
     * @param profileData map containing profile data fields (bio, skills, availability, preferences)
     * @return true if the profile was successfully updated, false otherwise
     */
    boolean updateProfile(Long volunteerId, Map<String, String> profileData);
    
    /**
     * Update volunteer skills
     * 
     * @param volunteerId the ID of the volunteer
     * @param skills the new skills
     * @return true if the skills were successfully updated, false otherwise
     */
    boolean updateSkills(Long volunteerId, String skills);
    
    /**
     * Update volunteer availability
     * 
     * @param volunteerId the ID of the volunteer
     * @param availability the new availability
     * @return true if the availability was successfully updated, false otherwise
     */
    boolean updateAvailability(Long volunteerId, String availability);
    
    /**
     * Get statistics for a volunteer
     * 
     * @param volunteerId the ID of the volunteer
     * @return map containing volunteer statistics
     */
    Map<String, Object> getVolunteerStats(Long volunteerId);
    
    /**
     * Search for volunteers based on criteria
     * 
     * @param criteria map containing search criteria (skills, availability, status)
     * @return list of volunteers matching the criteria
     */
    List<Volunteer> searchVolunteers(Map<String, String> criteria);
}
