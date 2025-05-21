package com.example.VoloConnect.repository;

import com.example.VoloConnect.models.Query;
import com.example.VoloConnect.models.Query.QueryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
    
    // Find queries by status
    List<Query> findByStatus(QueryStatus status);
    
    // Find queries by email
    List<Query> findByEmailContainingIgnoreCase(String email);
    
    // Find queries by name
    List<Query> findByNameContainingIgnoreCase(String name);
    
    // Find queries by subject containing keyword
    List<Query> findBySubjectContainingIgnoreCase(String keyword);
    
    // Count queries by status
    long countByStatus(QueryStatus status);
}
