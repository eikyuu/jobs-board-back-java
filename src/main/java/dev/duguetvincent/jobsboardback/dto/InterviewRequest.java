package dev.duguetvincent.jobsboardback.dto;

import dev.duguetvincent.jobsboardback.entity.InterviewType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InterviewRequest {

    private LocalDate date;
    private InterviewType type;
    private String notes;
}
