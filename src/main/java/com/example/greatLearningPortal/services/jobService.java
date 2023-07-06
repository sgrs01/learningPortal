package com.example.greatLearningPortal.services;

import com.example.greatLearningPortal.models.*;
import com.example.greatLearningPortal.repositories.JobRepository;
import com.example.greatLearningPortal.repositories.JobSkillRepository;
import com.example.greatLearningPortal.repositories.SkillRepository;
import com.example.greatLearningPortal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class jobService {

    private JobRepository jobRepository;

    private SkillRepository skillRepository;

    private UserRepository userRepository;

    private final JobSkillRepository jobSkillRepository;

    public jobService(JobRepository jobRepository, SkillRepository skillRepository,UserRepository userRepository, JobSkillRepository jobSkillRepository) {
        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
        this.jobSkillRepository = jobSkillRepository;
    }

    public List<User>  getSuitableCandidateByJobId(Long jobId)
    {
        //Search all the jobSkills relevant to that jobId from jobSkill table,
        List<Skill> skillsByJobId = skillRepository.findByJobId(jobId);

        //Fetch all the mandatory jobSkills relevant to that jobId,
        List<Skill> mandatorySkills = jobSkillRepository.findMandatorySkillsByJobId(jobId);

        //Based on the mandatory skill ids, fetch all the userId from the table having those mandatory skills
        List<Long> mandatorySkillIds = mandatorySkills.stream().map(Skill::getId).collect(Collectors.toList());
        List<User> relevantUsers = userRepository.findBySkillsIdIn(mandatorySkillIds);


        //Based on userIds received , calculate the allSkillScore(summation of mandatory and optional job skill score) for each user
        Map<Long, Integer> userSkillSumMap = new HashMap<>();
        List<Long> allSkillIds = skillsByJobId.stream().map(Skill::getId).collect(Collectors.toList());
        for (User user : relevantUsers) {
            int skillSum = user.getSkillScoreSum(allSkillIds); // Function to get sum of only skills related to jobId for each user
            userSkillSumMap.put(user.getId(), skillSum);
        }

        //Sort users based on skillsSum
        LinkedHashMap<Long, Integer> sortedMap = userSkillSumMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));

        //Retrieve users based on userIds
        List<User> sortedRelevantUsers = sortedMap.entrySet().stream()
                .map(entry -> userRepository.getUserById(entry.getKey()))
                .collect(Collectors.toList());
        //return that list

        return sortedRelevantUsers;

    }
}
