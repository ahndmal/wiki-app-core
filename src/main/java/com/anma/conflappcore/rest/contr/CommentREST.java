package com.anma.conflappcore.rest.contr;

import com.anma.conflappcore.models.db.Comment;
import com.anma.conflappcore.repo.CommentRepo;
import com.anma.conflappcore.rest.dto.CommentDTO;
import com.anma.conflappcore.rest.req.CreateCommentReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/comments")
public class CommentREST {

    @Autowired
    private final CommentRepo commentRepo;

    public CommentREST(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @GetMapping()
    public List<Comment> getComments() {
        return commentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable long id) {
        return commentRepo.getById(id);
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
        edited.setUserId(1); // todo - AUTH used
        var saved = commentRepo.save(edited);
        return new CommentDTO(saved.getId(), saved.getBody(), saved.getCreatedAt(), saved.getUserId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePage(@PathVariable long id) {
        commentRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted workout with id = " + id);
    }
}
