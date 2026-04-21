package dev.duguetvincent.jobsboardback.dto;

import dev.duguetvincent.jobsboardback.entity.ContractType;
import dev.duguetvincent.jobsboardback.entity.JobStatus;
import dev.duguetvincent.jobsboardback.entity.RemoteType;
import dev.duguetvincent.jobsboardback.entity.Salary;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreateJobRequest {

    private String title;
    private String company;
    private String location;
    private RemoteType remote;
    private ContractType contractType;
    private Salary salary;
    private JobStatus status;
    private LocalDate appliedAt;
    private String url;
    private String description;
    private List<String> tags;
    private String notes;
}
