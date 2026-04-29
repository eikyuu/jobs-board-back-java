package dev.duguetvincent.jobsboardback.repository;

import dev.duguetvincent.jobsboardback.entity.Job;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, String> {

    @EntityGraph(attributePaths = {"interviews"})
    List<Job> findAll();

    @EntityGraph(attributePaths = {"interviews"})
    Optional<Job> findById(String id);
}
