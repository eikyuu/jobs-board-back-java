package dev.duguetvincent.jobsboardback.dto;

import dev.duguetvincent.jobsboardback.entity.InterviewType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class InterviewResponse {

    private String id;
    private LocalDate date;
    private InterviewType type;
    private String notes;
}
