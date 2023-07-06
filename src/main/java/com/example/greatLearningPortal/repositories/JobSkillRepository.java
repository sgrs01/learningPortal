package com.example.greatLearningPortal.repositories;

import com.example.greatLearningPortal.models.JobSkills;
import com.example.greatLearningPortal.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobSkillRepository extends JpaRepository<JobSkills, Long> {
    @Query("SELECT DISTINCT js.mandatorySkill FROM JobSkill js WHERE js.job.id = :jobId")
    List<Skill> findMandatorySkillsByJobId(@Param("jobId") Long jobId);
}
