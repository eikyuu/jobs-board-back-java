package dev.duguetvincent.jobsboardback.dto;

import dev.duguetvincent.jobsboardback.entity.InterviewType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class InterviewResponse {

    private String id;
    private LocalDateTime scheduledAt;
    private InterviewType type;
    private String notes;
}
