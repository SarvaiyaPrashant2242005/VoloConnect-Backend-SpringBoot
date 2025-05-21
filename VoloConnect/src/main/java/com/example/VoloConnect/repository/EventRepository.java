package com.example.VoloConnect.repository;

import com.example.VoloConnect.models.Event;
import com.example.VoloConnect.models.Event.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    
    // Find events by status
    List<Event> findByStatus(EventStatus status);
    
    // Find events by date range
    List<Event> findByDateBetween(Date start, Date end);
    
    // Find events by location
    List<Event> findByLocationContainingIgnoreCase(String location);
    
    // Find events by title containing keyword
    List<Event> findByTitleContainingIgnoreCase(String keyword);
    
    // Find upcoming events (date in the future)
    List<Event> findByDateAfter(Date date);
    
    // Find events that have capacity available (custom query would be better here)
    // This is a placeholder as actual capacity check would need more complex logic
    List<Event> findByCapacityGreaterThan(int minimumCapacity);
}
