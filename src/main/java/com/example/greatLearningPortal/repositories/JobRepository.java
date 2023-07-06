package com.example.greatLearningPortal.repositories;
import com.example.greatLearningPortal.models.JobSkills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobSkills,Long> {
}
