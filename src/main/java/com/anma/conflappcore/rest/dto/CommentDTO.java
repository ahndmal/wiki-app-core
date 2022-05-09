package com.anma.conflappcore.rest.dto;

import java.time.LocalDateTime;

public record CommentDTO(
        long id,
        String body,
        LocalDateTime createdAt,
        long authorId
) {
}
