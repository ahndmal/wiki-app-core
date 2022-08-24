package com.anma.conflappcore.rest.req;

public record CreateCommentReq(
        String body,
        String title,
        long parentId
) {
}
