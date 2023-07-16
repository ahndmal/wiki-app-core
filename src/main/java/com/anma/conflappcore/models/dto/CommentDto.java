package com.anma.conflappcore.models.dto;

import java.time.LocalDateTime;

public record CommentDto(
        long id,
        String body,
        LocalDateTime createdAt,
        long authorId
) {
}
