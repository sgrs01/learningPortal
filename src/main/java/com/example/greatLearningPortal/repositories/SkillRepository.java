package com.example.greatLearningPortal.repositories;

import com.example.greatLearningPortal.models.Job;
import com.example.greatLearningPortal.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill,Long> {
    List<Skill> findByJobId(Long skillId);
}
