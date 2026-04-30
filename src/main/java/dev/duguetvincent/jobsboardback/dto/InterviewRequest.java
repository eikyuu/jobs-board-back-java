package dev.duguetvincent.jobsboardback.dto;

import dev.duguetvincent.jobsboardback.entity.InterviewType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InterviewRequest {

    private LocalDateTime scheduledAt;
    private InterviewType type;
    private String notes;
}
