package com.sis.retro.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sis.retro.model.FeedBackType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@XmlRootElement(name = "root")
public class FeedBackResponse {
    @JsonProperty
    private String name;
    @JsonProperty
    private String body;
    @JsonProperty
    private FeedBackType type;
}
