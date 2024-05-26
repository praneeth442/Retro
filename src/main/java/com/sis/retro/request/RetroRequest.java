package com.sis.retro.request;

import com.sis.retro.validation.DateValidatorConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Builder
@Data
public class RetroRequest {

    @NotBlank(message = "retro name is mandatory")
    private String retroName;
    private String summary;
    @DateValidatorConstraint(message="invalid date or date format expected date format is dd/mm/yyyy")
    private String date;
    @Size(min=1,message= "minimum one participant name is required")
    @NotNull
    private List<String> participants;
}
