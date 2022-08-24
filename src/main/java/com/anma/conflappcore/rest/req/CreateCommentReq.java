package com.anma.conflappcore.rest.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCommentReq(
        String body,
        String title,
        @JsonProperty(value = "parent_id")
        long parentId
) {
}
