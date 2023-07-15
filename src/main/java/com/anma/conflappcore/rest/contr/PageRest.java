package com.anma.conflappcore.rest.contr;

import com.anma.conflappcore.models.ContentWeb;
import com.anma.conflappcore.models.db.Page;
import com.anma.conflappcore.repo.PageRepo;
import com.anma.conflappcore.rest.dto.ContentType;
import com.anma.conflappcore.rest.dto.PageDTO;
import com.anma.conflappcore.rest.req.CreatePageReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/api/pages")
public class PageRest {
    private static final Logger LOG = LoggerFactory.getLogger(PageRest.class);
    private final PageRepo pageRepo;
   
    @Autowired
    public PageRest(PageRepo pageRepo) {
        this.pageRepo = pageRepo;
    }

    @GetMapping()
    public List<Page> getPages() {
        return pageRepo.findAll().stream().limit(10000).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ContentWeb getPageById(@PathVariable long id) {
        Page page = pageRepo.getById(id);
        var content = new ContentWeb(id, page.getTitle(),
                ContentType.PAGE.name(),
                page.getBody(),
                page.getSpaceKey(),
                String.valueOf(page.getId()),
                page.getCreatedAt(),
                page.getLastUpdated());
        return content;
    }

    @GetMapping("/{key}/{title}")
    public ContentWeb getPageBySpacekeyAndTitle(@PathVariable String key, @PathVariable String title) {
        LOG.warn(">> getPageBySpacekeyAndTitle");
        LOG.warn(">> key: " + key);
        LOG.warn(">> title: " + title);
        Page page = pageRepo.findByTitleAndSpaceKey(title, key);
        LOG.warn(page.toString());

        var content = new ContentWeb(
                page.getId(),
                page.getTitle(),
                ContentType.PAGE.name(),
                page.getBody(),
                page.getSpaceKey(),
                String.valueOf(page.getId()),
                page.getCreatedAt(),
                page.getLastUpdated());
        return content;
    }

    @PostMapping
    public Page createPage( @RequestBody CreatePageReq dto) {
        var page = new Page();
        page.setBody(dto.body());
        page.setTitle(dto.title());
        page.setCreatedAt(LocalDateTime.now());
        page.setAuthorId(new Random().nextInt(1,5)); // todo
        page.setSpaceKey(dto.spaceKey());
        return pageRepo.save(page);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PageDTO savePage(@PathVariable long id, @RequestBody CreatePageReq dto) {
        var edited = pageRepo.getById(id);
        edited.setTitle(dto.title());
        edited.setBody(dto.body());
        Page savedPage = pageRepo.save(edited);
        return new PageDTO(savedPage.getId(), savedPage.getTitle(), savedPage.getBody(), savedPage.getSpaceKey());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePage(@PathVariable long id) {
        pageRepo.deleteById(id);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body("Deleted workout with id = " + id);
    }
}
