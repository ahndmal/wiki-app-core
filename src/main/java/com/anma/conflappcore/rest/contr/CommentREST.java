package com.anma.conflappcore.rest.contr;

import com.anma.conflappcore.models.ContentWeb;
import com.anma.conflappcore.models.db.Comment;
import com.anma.conflappcore.models.db.Page;
import com.anma.conflappcore.repo.CommentRepo;
import com.anma.conflappcore.repo.PageRepo;
import com.anma.conflappcore.rest.dto.CommentDTO;
import com.anma.conflappcore.rest.req.CreateCommentReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/rest/api/comments")
public class CommentREST {

    @Autowired
    private final CommentRepo commentRepo;
    private final PageRepo pageRepo;

    public CommentREST(CommentRepo commentRepo, PageRepo pageRepo) {
        this.commentRepo = commentRepo;
        this.pageRepo = pageRepo;
    }

    @GetMapping()
    public List<Comment> getComments() {
        return commentRepo.findAll();
    }

    @GetMapping("/{id}")
    public ContentWeb getComment(@PathVariable long id) {
        var comment = commentRepo.getById(id);
        Page page = null;
        try {
            page = pageRepo.getById(comment.getParentId());
            System.out.println(page);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        String parentId = page == null ? "" : String.valueOf(page.getId());
        String spaceKey = page == null ? "" : page.getSpaceKey();
        var content = new ContentWeb(id, comment.getTitle(), "comment", comment.getBody(), spaceKey,
                parentId,
                comment.getCreatedAt(),
                comment.getLastEdited());
        return content;
    }

    @PostMapping
    public Comment createComment(@RequestBody CreateCommentReq dto) {
        var comment = new Comment();
        comment.setBody(dto.body());
        comment.setTitle(dto.title());
        comment.setParentId(dto.parentId());
        return commentRepo.save(comment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CommentDTO saveSpace(@PathVariable long id, @RequestBody CreateCommentReq dto) {
        var edited = commentRepo.getById(id);
        edited.setBody(dto.body());
        edited.setUserId(new Random().nextInt(1,5)); // todo - AUTH used
        var saved = commentRepo.save(edited);
        return new CommentDTO(saved.getId(), saved.getBody(), saved.getCreatedAt(), saved.getUserId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePage(@PathVariable long id) {
        commentRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted workout with id = " + id);
    }
}
