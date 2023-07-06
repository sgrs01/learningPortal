package com.example.greatLearningPortal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "JOB")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name= "JOB_SKILL_Table",joinColumns = {
            @JoinColumn(name = "job_id",referencedColumnName = "id")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "skill_id", referencedColumnName = "id")
    })
    private List<Skill> skill;


    @OneToMany(mappedBy = "job")
    private List<JobSkills> jobSkills;

    @Column(name = "mandatory_skill")
    private String mandatorySkill;
}

