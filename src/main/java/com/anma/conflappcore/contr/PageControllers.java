package com.anma.conflappcore.contr;

import com.anma.conflappcore.repo.CommentRepo;
import com.anma.conflappcore.repo.PageRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageControllers {
    private final PageRepo pageRepo;
    private final CommentRepo commentRepo;

    public PageControllers(PageRepo pageRepo, CommentRepo commentRepo) {
        this.pageRepo = pageRepo;
        this.commentRepo = commentRepo;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/pages/{spaceKey}/{pageTitle}")
    public String getPageKeyTitle(Model model, @PathVariable String spaceKey, @PathVariable String pageTitle) {

        var page = pageRepo.findByTitleAndSpaceKey(pageTitle, spaceKey);
        model.addAttribute("page", page);

        return "page";
    }
    @GetMapping("/viewpage")
    public String getPageId(Model model, @RequestParam long pageId) {

        var page = pageRepo.findById(pageId).get();
        model.addAttribute("page", page);
        model.addAttribute("comments", commentRepo.findCommentByParentId(pageId));

        return "page";
    }

    @GetMapping("editpage/{pageId}")
    public String editPage(@PathVariable long pageId, Model model) {

        var page = pageRepo.findById(pageId).get();
        model.addAttribute("page", page);
        model.addAttribute("pageId", pageId);

        return "edit-page";
    }

    @GetMapping("/pages/all")
    public String getPages(Model model) {
        model.addAttribute("pages", pageRepo.findAll());
        return "pages";
    }

}
