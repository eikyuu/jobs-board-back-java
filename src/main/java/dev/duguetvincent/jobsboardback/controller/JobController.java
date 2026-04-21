package dev.duguetvincent.jobsboardback.controller;

import dev.duguetvincent.jobsboardback.dto.CreateJobRequest;
import dev.duguetvincent.jobsboardback.entity.Job;
import dev.duguetvincent.jobsboardback.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public List<Job> getAll() {
        return jobService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getById(@PathVariable String id) {
        return jobService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Job> create(@RequestBody CreateJobRequest request) {
        Job created = jobService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
