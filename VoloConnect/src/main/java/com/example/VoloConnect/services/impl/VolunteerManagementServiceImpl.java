package com.example.VoloConnect.services.impl;

import com.example.VoloConnect.models.Event;
import com.example.VoloConnect.models.EventVolunteer;
import com.example.VoloConnect.models.Volunteer;
import com.example.VoloConnect.models.Volunteer.VolunteerStatus;
import com.example.VoloConnect.repository.EventRepository;
import com.example.VoloConnect.repository.EventVolunteerRepository;
import com.example.VoloConnect.repository.VolunteerRepository;
import com.example.VoloConnect.services.VolunteerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VolunteerManagementServiceImpl implements VolunteerManagementService {

    @Autowired
    private VolunteerRepository volunteerRepository;
    
    @Autowired
    private EventVolunteerRepository eventVolunteerRepository;
    
    @Autowired
    private EventRepository eventRepository;

    @Override
    @Transactional
    public boolean updateProfile(Long volunteerId, Map<String, String> profileData) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
        
        if (profileData.containsKey("bio")) {
            volunteer.setBio(profileData.get("bio"));
        }
        
        if (profileData.containsKey("skills")) {
            volunteer.setSkills(profileData.get("skills"));
        }
        
        if (profileData.containsKey("availability")) {
            volunteer.setAvailability(profileData.get("availability"));
        }
        
        if (profileData.containsKey("preferences")) {
            volunteer.setPreferences(profileData.get("preferences"));
        }
        
        volunteerRepository.save(volunteer);
        return true;
    }

    @Override
    @Transactional
    public boolean updateSkills(Long volunteerId, String skills) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
        
        volunteer.setSkills(skills);
        volunteerRepository.save(volunteer);
        return true;
    }

    @Override
    @Transactional
    public boolean updateAvailability(Long volunteerId, String availability) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
        
        volunteer.setAvailability(availability);
        volunteerRepository.save(volunteer);
        return true;
    }

    @Override
    public Map<String, Object> getVolunteerStats(Long volunteerId) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
        
        List<EventVolunteer> eventVolunteers = eventVolunteerRepository.findByVolunteer(volunteer);
        
        int totalEvents = eventVolunteers.size();
        
        // Count completed events
        int completedEvents = 0;
        List<String> roles = new ArrayList<>();
        
        for (EventVolunteer ev : eventVolunteers) {
            if (ev.getEvent().getStatus() == Event.EventStatus.COMPLETED) {
                completedEvents++;
            }
            
            if (!roles.contains(ev.getRole())) {
                roles.add(ev.getRole());
            }
        }
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalEvents", totalEvents);
        stats.put("completedEvents", completedEvents);
        stats.put("roles", String.join(", ", roles));
        
        return stats;
    }

    @Override
    public List<Volunteer> searchVolunteers(Map<String, String> criteria) {
        List<Volunteer> allVolunteers = volunteerRepository.findAll();
        
        return allVolunteers.stream()
                .filter(volunteer -> matchesCriteria(volunteer, criteria))
                .collect(Collectors.toList());
    }
    
    private boolean matchesCriteria(Volunteer volunteer, Map<String, String> criteria) {
        if (criteria.containsKey("skills") && (volunteer.getSkills() == null || 
                !volunteer.getSkills().toLowerCase().contains(criteria.get("skills").toLowerCase()))) {
            return false;
        }
        
        if (criteria.containsKey("availability") && (volunteer.getAvailability() == null || 
                !volunteer.getAvailability().toLowerCase().contains(criteria.get("availability").toLowerCase()))) {
            return false;
        }
        
        if (criteria.containsKey("status") && !volunteer.getStatus().name().equalsIgnoreCase(criteria.get("status"))) {
            return false;
        }
        
        return true;
    }
}
