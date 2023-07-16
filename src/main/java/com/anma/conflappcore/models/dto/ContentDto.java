package com.anma.conflappcore.models.dto;

import java.time.LocalDateTime;

public record ContentDto(
        long id,
        String title,
        String type,
        String body,
        String spaceKey,
        String parentId,
        LocalDateTime createdAt,
        LocalDateTime lastUpdated

) {
}
