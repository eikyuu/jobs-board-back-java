package dev.duguetvincent.jobsboardback.dto;

import dev.duguetvincent.jobsboardback.entity.ContractType;
import dev.duguetvincent.jobsboardback.entity.JobStatus;
import dev.duguetvincent.jobsboardback.entity.RemoteType;
import dev.duguetvincent.jobsboardback.entity.Salary;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class JobResponse {

    private String id;
    private String title;
    private String company;
    private String location;
    private RemoteType remote;
    private ContractType contractType;
    private Salary salary;
    private JobStatus status;
    private LocalDate appliedAt;
    private LocalDateTime updatedAt;
    private String url;
    private List<String> tags;
    private String notes;
    private List<InterviewResponse> interviews;
}
