package com.anma.conflappcore.contr;

import com.anma.conflappcore.repo.BlogRepo;
import com.anma.conflappcore.repo.CommentRepo;
import com.anma.conflappcore.repo.PageRepo;
import com.anma.conflappcore.repo.SpaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MiscControllers {
    private final PageRepo pageRepo;
    private final SpaceRepo spaceRepo;
    private final CommentRepo commentRepo;
    private final BlogRepo blogRepo;

    @Autowired
    public MiscControllers(PageRepo pageRepo, SpaceRepo spaceRepo, CommentRepo commentRepo, BlogRepo blogRepo) {
        this.pageRepo = pageRepo;
        this.spaceRepo = spaceRepo;
        this.commentRepo = commentRepo;
        this.blogRepo = blogRepo;
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        return "profile";
    }

    @GetMapping("/stats")
    public String getStats(Model model) {
        model.addAttribute("pages", pageRepo.count());
        model.addAttribute("spaces", spaceRepo.count());
        model.addAttribute("blogs", blogRepo.count());
        model.addAttribute("comments", commentRepo.count());
        return "stats";
    }
}
