package com.example.greatLearningPortal.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "SKILL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "skills")
    private List<User> users;

    @ManyToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private List<Job> jobs;

    @OneToMany(mappedBy = "skill")
    private List<JobSkills> jobSkills;
}

