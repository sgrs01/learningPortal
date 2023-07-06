package com.example.greatLearningPortal.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String questions;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @OneToMany(mappedBy = "quizz")
    private List<UserCourseQuizz> userCourseQuizzes;

    // Getters and setters (omitted for brevity)
}
