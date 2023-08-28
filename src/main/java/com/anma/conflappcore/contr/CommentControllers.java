package com.anma.conflappcore.contr;

import com.anma.conflappcore.repo.CommentRepo;
import com.anma.conflappcore.repo.PageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentControllers {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getName());
    private final CommentRepo commentRepo;
    private final PageRepo pageRepo;

    @Autowired
    public CommentControllers(CommentRepo commentRepo, PageRepo pageRepo) {
        this.commentRepo = commentRepo;
        this.pageRepo = pageRepo;
    }

    @GetMapping("/comment/{pageId}/{commId}")
    public String getComment(Model model, @PathVariable long pageId, @PathVariable long commId) {
        LOG.info("performing /comment/{pageId}/{commId} GET request");
        model.addAttribute("page", pageRepo.findById(pageId).get());
        var comment = commentRepo.getById(commId);
        model.addAttribute("comment", comment);
        return "comment/comment";
    }

    @GetMapping("edit-comment/{pageId}/{commId}")
    public String EditComment(@PathVariable long pageId, @PathVariable long commId, Model model) {
        var page = pageRepo.findById(pageId).get();
        model.addAttribute("page", page);
        model.addAttribute("$pageId", pageId);
        model.addAttribute("commId", commId);
        model.addAttribute("comment", commentRepo.getById(commId));

        return "comment/edit-comment";
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        var comments = commentRepo.findAll();
        if (comments.size() > 1000) {
            LOG.info(">>> serving COMMENTS page with < 1000 comments");
            model.addAttribute("comments", comments.stream().takeWhile(c -> c.getId() < 1000).toList());
        } else {
            LOG.info(">>> serving COMMENTS page with ALL comments");
            model.addAttribute("comments", comments);
        }

        return "comment/comments";
    }

}
