package com.example.greatLearningPortal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Course> courses;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name= "USER_SKILL_Table",joinColumns = {
            @JoinColumn(name = "user_id",referencedColumnName = "id")
    }, inverseJoinColumns = {
                    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    })
    @MapKeyColumn(name = "skill_id")
    @Column(name = "skill_score")
    private Map<Skill, Integer> skills;

    //This function filters all the skills of an user and sums only score of the skills passed as parameter skillIds
    public int getSkillScoreSum(List<Long> skillIds) {
        return skills.entrySet().stream()
                .filter(entry -> skillIds.contains(entry.getKey().getId()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
