package com.example.greatLearningPortal.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    @One
    ToMany(mappedBy = "course")
    private List<Quiz> quizzes;

    // Getters and setters (omitted for brevity)
}

