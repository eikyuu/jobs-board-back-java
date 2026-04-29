package dev.duguetvincent.jobsboardback.service;

import dev.duguetvincent.jobsboardback.dto.CreateJobRequest;
import dev.duguetvincent.jobsboardback.dto.InterviewRequest;
import dev.duguetvincent.jobsboardback.dto.InterviewResponse;
import dev.duguetvincent.jobsboardback.dto.JobResponse;
import dev.duguetvincent.jobsboardback.entity.Interview;
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

    public List<JobResponse> findAll() {
        return jobRepository.findAll().stream().map(this::toJobResponse).toList();
    }

    public Optional<JobResponse> findById(String id) {
        return jobRepository.findById(id).map(this::toJobResponse);
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

            job.getInterviews().clear();
            if (request.getInterviews() != null) {
                request.getInterviews().stream()
                        .map(dto -> toInterview(dto, job))
                        .forEach(job.getInterviews()::add);
            }

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

        if (request.getInterviews() != null) {
            request.getInterviews().stream()
                    .map(dto -> toInterview(dto, job))
                    .forEach(job.getInterviews()::add);
        }

        return jobRepository.save(job);
    }

    private Interview toInterview(InterviewRequest dto, Job job) {
        return Interview.builder()
                .date(dto.getDate())
                .type(dto.getType())
                .notes(dto.getNotes())
                .job(job)
                .build();
    }

    private JobResponse toJobResponse(Job job) {
        List<InterviewResponse> interviews = job.getInterviews().stream()
                .map(i -> InterviewResponse.builder()
                        .id(i.getId())
                        .date(i.getDate())
                        .type(i.getType())
                        .notes(i.getNotes())
                        .build())
                .toList();

        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .company(job.getCompany())
                .location(job.getLocation())
                .remote(job.getRemote())
                .contractType(job.getContractType())
                .salary(job.getSalary())
                .status(job.getStatus())
                .appliedAt(job.getAppliedAt())
                .updatedAt(job.getUpdatedAt())
                .url(job.getUrl())
                .tags(job.getTags())
                .notes(job.getNotes())
                .interviews(interviews)
                .build();
    }
}
