package com.example.VoloConnect.services.impl;

import com.example.VoloConnect.models.Event;
import com.example.VoloConnect.models.EventVolunteer;
import com.example.VoloConnect.models.Volunteer;
import com.example.VoloConnect.repository.EventRepository;
import com.example.VoloConnect.repository.EventVolunteerRepository;
import com.example.VoloConnect.repository.VolunteerRepository;
import com.example.VoloConnect.services.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventManagementServiceImpl implements EventManagementService {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private VolunteerRepository volunteerRepository;
    
    @Autowired
    private EventVolunteerRepository eventVolunteerRepository;

    @Override
    @Transactional
    public Long assignVolunteer(Long eventId, Long volunteerId, String role) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
                
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
        
        // Check if the assignment already exists
        Optional<EventVolunteer> existingAssignment = 
                eventVolunteerRepository.findByEventAndVolunteer(event, volunteer);
        
        if (existingAssignment.isPresent()) {
            throw new RuntimeException("Volunteer is already assigned to this event");
        }
        
        EventVolunteer eventVolunteer = new EventVolunteer();
        eventVolunteer.setEvent(event);
        eventVolunteer.setVolunteer(volunteer);
        eventVolunteer.setRole(role);
        
        eventVolunteer = eventVolunteerRepository.save(eventVolunteer);
        return eventVolunteer.getId();
    }

    @Override
    @Transactional
    public boolean removeVolunteer(Long eventId, Long volunteerId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
                
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
        
        Optional<EventVolunteer> eventVolunteerOpt = 
                eventVolunteerRepository.findByEventAndVolunteer(event, volunteer);
                
        if (eventVolunteerOpt.isPresent()) {
            eventVolunteerRepository.delete(eventVolunteerOpt.get());
            return true;
        }
        
        return false;
    }

    @Override
    public List<Volunteer> getEventVolunteers(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
                
        List<EventVolunteer> eventVolunteers = eventVolunteerRepository.findByEvent(event);
        
        return eventVolunteers.stream()
                .map(EventVolunteer::getVolunteer)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getVolunteerEvents(Long volunteerId) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
                
        List<EventVolunteer> eventVolunteers = eventVolunteerRepository.findByVolunteer(volunteer);
        
        return eventVolunteers.stream()
                .map(EventVolunteer::getEvent)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean updateVolunteerRole(Long eventId, Long volunteerId, String newRole) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
                
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
        
        Optional<EventVolunteer> eventVolunteerOpt = 
                eventVolunteerRepository.findByEventAndVolunteer(event, volunteer);
                
        if (eventVolunteerOpt.isPresent()) {
            EventVolunteer eventVolunteer = eventVolunteerOpt.get();
            eventVolunteer.setRole(newRole);
            eventVolunteerRepository.save(eventVolunteer);
            return true;
        }
        
        return false;
    }
}
