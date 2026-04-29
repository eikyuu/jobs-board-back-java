package dev.duguetvincent.jobsboardback.controller;

import dev.duguetvincent.jobsboardback.dto.CreateJobRequest;
import dev.duguetvincent.jobsboardback.dto.JobResponse;
import dev.duguetvincent.jobsboardback.entity.Job;
import dev.duguetvincent.jobsboardback.service.JobService;
import dev.duguetvincent.jobsboardback.service.PdfExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
    private final PdfExportService pdfExportService;

    @GetMapping
    public List<JobResponse> getAll() {
        return jobService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getById(@PathVariable String id) {
        return jobService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Job> create(@RequestBody CreateJobRequest request) {
        Job created = jobService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable String id, @RequestBody CreateJobRequest request) {
        return jobService.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return jobService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportPdf() {
        byte[] pdf = pdfExportService.exportJobs(jobService.findAll());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "jobs-export.pdf");
        return ResponseEntity.ok().headers(headers).body(pdf);
    }

}

