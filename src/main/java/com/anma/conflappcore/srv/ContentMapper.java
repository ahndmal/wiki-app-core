package com.anma.conflappcore.srv;

import com.anma.conflappcore.models.db.WikiPage;
import com.anma.conflappcore.models.dto.ContentDto;
import com.anma.conflappcore.models.dto.ContentType;
import com.anma.conflappcore.models.dto.PageDto;

public class ContentMapper {
    public static PageDto convert(WikiPage page) {
        return new PageDto(
                page.getId(),
                page.getTitle(),
                page.getBody(),
                page.getSpaceKey()
        );
    }

    public static ContentDto contentDto(WikiPage page) {
        return new ContentDto(
                page.getId(),
                page.getTitle(),
                ContentType.PAGE.name(),
                page.getBody(),
                page.getSpaceKey(),
                String.valueOf(page.getId()),
                page.getCreatedAt(),
                page.getLastUpdated());
    }
}
