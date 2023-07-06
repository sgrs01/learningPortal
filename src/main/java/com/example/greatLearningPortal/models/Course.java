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

    @OneToMany(mappedBy = "course")
    private List<Quiz> quizzes;

}

