package com.example.VoloConnect.repository;

import com.example.VoloConnect.models.Volunteer;
import com.example.VoloConnect.models.Volunteer.VolunteerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    
    // Find by email
    Optional<Volunteer> findByEmail(String email);
    
    // Find volunteers by status
    List<Volunteer> findByStatus(VolunteerStatus status);
    
    // Find volunteers by name containing keyword
    List<Volunteer> findByNameContainingIgnoreCase(String keyword);
    
    // Find volunteers by skills containing keyword
    List<Volunteer> findBySkillsContainingIgnoreCase(String skills);
    
    // Find volunteers by phone number
    Optional<Volunteer> findByPhone(String phone);
    
    // Find by name and email
    Optional<Volunteer> findByNameAndEmail(String name, String email);
    
    // Count volunteers by status
    long countByStatus(VolunteerStatus status);
}
