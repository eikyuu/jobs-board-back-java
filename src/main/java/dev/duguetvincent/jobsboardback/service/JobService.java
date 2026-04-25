package dev.duguetvincent.jobsboardback.service;

import dev.duguetvincent.jobsboardback.dto.CreateJobRequest;
import dev.duguetvincent.jobsboardback.entity.Job;
import dev.duguetvincent.jobsboardback.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobService {

    private final JobRepository jobRepository;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Optional<Job> findById(String id) {
        return jobRepository.findById(id);
    }

    @Transactional
    public boolean delete(String id) {
        if (!jobRepository.existsById(id)) {
            return false;
        }
        jobRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Optional<Job> update(String id, CreateJobRequest request) {
        return jobRepository.findById(id).map(job -> {
            job.setTitle(request.getTitle());
            job.setCompany(request.getCompany());
            job.setLocation(request.getLocation());
            job.setRemote(request.getRemote());
            job.setContractType(request.getContractType());
            job.setSalary(request.getSalary());
            job.setStatus(request.getStatus());
            job.setAppliedAt(request.getAppliedAt());
            job.setUrl(request.getUrl());
            job.setTags(request.getTags() != null ? request.getTags() : new ArrayList<>());
            job.setNotes(request.getNotes());
            job.setUpdatedAt(LocalDateTime.now());
            return jobRepository.save(job);
        });
    }

    @Transactional
    public Job create(CreateJobRequest request) {
        Job job = Job.builder()
                .title(request.getTitle())
                .company(request.getCompany())
                .location(request.getLocation())
                .remote(request.getRemote())
                .contractType(request.getContractType())
                .salary(request.getSalary())
                .status(request.getStatus())
                .appliedAt(request.getAppliedAt())
                .url(request.getUrl())
                .tags(request.getTags() != null ? request.getTags() : new ArrayList<>())
                .notes(request.getNotes())
                .updatedAt(LocalDateTime.now())
                .build();

        return jobRepository.save(job);
    }
}
