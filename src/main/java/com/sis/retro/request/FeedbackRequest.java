package com.sis.retro.request;

import com.sis.retro.validation.FeedbackTypeConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class FeedbackRequest {

    @NotNull(message = "feedback name is mandatory")
    private String feedBackName;
    private String body;
    @NotNull
    @FeedbackTypeConstraint(message = "accepted values for the feedback type positive,negative,idea,praise")
    private String type;
    @NotNull(message = "retro name is mandatory")
    private String retroName;

}
