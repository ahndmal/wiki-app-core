package com.anma.conflappcore.models.dto;

public record SpaceDto(
        long id,
        String title,
        String category,
        String key,
        long authorId
) {
}
