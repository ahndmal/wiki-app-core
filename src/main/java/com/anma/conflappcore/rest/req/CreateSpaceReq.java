package com.anma.conflappcore.rest.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateSpaceReq(
        @JsonProperty(value = "title")
        String title,
        String category,
        @JsonProperty(value = "space_key")
        String spaceKey,
        LocalDateTime lastUpdated,
        @JsonProperty(value = "author_id")
        long authorId
) {
}
