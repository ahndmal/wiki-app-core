package com.anma.conflappcore.rest.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreatePageReq(
        @JsonProperty(value = "title")
        String title,
        String body
) {
}
