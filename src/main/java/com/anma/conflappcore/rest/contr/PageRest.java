package com.anma.conflappcore.rest.contr;

import com.anma.conflappcore.models.dto.ContentDto;
import com.anma.conflappcore.models.db.WikiPage;
import com.anma.conflappcore.repo.PageRepo;
import com.anma.conflappcore.models.dto.ContentType;
import com.anma.conflappcore.models.dto.PageDto;
import com.anma.conflappcore.rest.req.CreatePageReq;
import com.anma.conflappcore.srv.ContentMapper;
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

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
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
    public List<ContentDto> getPages() {
        return pageRepo.findAll().stream().limit(10000)
                .map(ContentMapper::contentDto).toList();
    }

    @GetMapping("/space/{space}")
    public List<ContentDto> getSpacePages(@PathVariable String space, @RequestParam(defaultValue = "300") int limit) {
        return pageRepo.findBySpaceKey(space).stream().limit(limit)
                .map(ContentMapper::contentDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ContentDto getPageById(@PathVariable long id) {
        WikiPage page = pageRepo.findById(id).orElseThrow(() -> new RuntimeException("No page with id " + id));
        return ContentMapper.contentDto(page);
    }

    @GetMapping("/{key}/{title}")
    public ResponseEntity<ContentDto> getPageBySpacekeyAndTitle(
            @PathVariable String key, @PathVariable String title) {
        LOG.info(">> getPageBySpacekeyAndTitle");
        LOG.info(">> key: " + key);
        LOG.info(">> title: " + title);
        WikiPage page = pageRepo.findByTitleAndSpaceKey(title, key);
        LOG.info(page.toString());
        var content = new ContentDto(
                page.getId(),
                page.getTitle(),
                ContentType.PAGE.name(),
                page.getBody(),
                page.getSpaceKey(),
                String.valueOf(page.getId()),
                page.getCreatedAt(),
                page.getLastUpdated());
        return ResponseEntity.status(200).body(content);
    }

    @PostMapping
    public ContentDto createPage(@RequestBody CreatePageReq dto) {
        var page = new WikiPage();
        page.setBody(dto.body());
        page.setTitle(dto.title());
        page.setCreatedAt(LocalDateTime.now());
        page.setAuthorId(new Random().nextInt(1, 5)); // todo
        page.setSpaceKey(dto.spaceKey());
        return ContentMapper.contentDto(pageRepo.save(page));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PageDto savePage(@PathVariable long id, @RequestBody CreatePageReq dto) {
        var edited = pageRepo.getById(id);
        edited.setTitle(dto.title());
        edited.setBody(dto.body());
        WikiPage savedPage = pageRepo.save(edited);
        return new PageDto(savedPage.getId(), savedPage.getTitle(), savedPage.getBody(), savedPage.getSpaceKey());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePage(@PathVariable long id) {
        pageRepo.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Deleted workout with id = " + id);
    }
}
