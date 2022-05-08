package com.anma.conflappcore.rest;

public record CreateSpaceReq(
        String title,
        String key,
        String category
) {
}
