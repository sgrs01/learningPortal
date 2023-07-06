package com.example.greatLearningPortal.repositories;

import com.example.greatLearningPortal.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository {
    List<User> findBySkillsIdIn(List<Long> skillIds);
    User getUserById(Long id);
}
