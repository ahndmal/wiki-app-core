package com.anma.conflappcore.models;

import java.time.LocalDateTime;

public record ContentWeb(
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
