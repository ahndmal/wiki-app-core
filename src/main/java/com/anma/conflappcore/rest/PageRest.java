package com.anma.conflappcore.rest;

import com.anma.conflappcore.models.ContentWeb;
import com.anma.conflappcore.models.db.Page;
import com.anma.conflappcore.repo.PageRepo;
import com.anma.conflappcore.rest.dto.PageDTO;
import com.anma.conflappcore.rest.req.CreatePageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rest/api/pages")
public class PageRest {

    private final PageRepo pageRepo;
    @Autowired
    public PageRest(PageRepo pageRepo) {
        this.pageRepo = pageRepo;
    }

    @GetMapping()
    public List<Page> getPages() {
        return pageRepo.findAll();
    }

    @GetMapping("/{id}")
    public ContentWeb getPageById(@PathVariable long id) {
        Page page = pageRepo.getById(id);
        var content = new ContentWeb(id, page.getTitle(), "page", page.getBody(), page.getSpaceKey());
        return content;
    }

    @PostMapping
    public Page createPage(PageDTO dto) {
        var page = new Page();
        page.setBody(dto.body());
        page.setTitle(dto.title());
        page.setCreatedAt(LocalDateTime.now());
        page.setAuthorId(1); // todo
        page.setSpaceKey(dto.spaceKey());
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
}
