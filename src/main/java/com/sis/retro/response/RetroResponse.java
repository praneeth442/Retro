package com.sis.retro.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;


@Builder
@Jacksonized
@XmlRootElement(name = "root")
public class RetroResponse {

    @JsonProperty
    @XmlAttribute
    private String name;
    @JsonProperty
    @XmlAttribute
    private String summary;
    @JsonProperty
    @XmlAttribute
    private String date;
    @JsonProperty
    @XmlAttribute
    private List<String> participants;
    @JsonProperty
    @XmlAttribute
    private List<FeedBackResponse> feedBacks;

}
