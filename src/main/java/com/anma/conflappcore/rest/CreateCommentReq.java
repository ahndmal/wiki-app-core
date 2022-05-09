package com.anma.conflappcore.rest;

public record CreateCommentReq(
        String body,
        String title,
        long parentId
) {
}
