package com.anma.conflappcore.contr;

import com.anma.conflappcore.events.ViewPagePublisher;
import com.anma.conflappcore.models.db.Page;
import com.anma.conflappcore.repo.CommentRepo;
import com.anma.conflappcore.repo.PageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageControllers {
    Logger LOG = LoggerFactory.getLogger(this.getClass().getName());
    private final PageRepo pageRepo;
    private final CommentRepo commentRepo;
    private final ViewPagePublisher viewPagePublisher;

    public PageControllers(PageRepo pageRepo, CommentRepo commentRepo, ViewPagePublisher viewPagePublisher) {
        this.pageRepo = pageRepo;
        this.commentRepo = commentRepo;
        this.viewPagePublisher = viewPagePublisher;
    }

    @GetMapping("/")
    public String home() {
        LOG.info("GET / home page");
        return "home";
    }

    @GetMapping("/pages/{spaceKey}/{pageTitle}")
    public String getPageKeyTitle(Model model, @PathVariable String spaceKey, @PathVariable String pageTitle) {
        viewPagePublisher.publishViewPageEvent(String.format("Page %s:%s viewed by ...", spaceKey, pageTitle));
        var page = pageRepo.findByTitleAndSpaceKey(pageTitle, spaceKey);
        LOG.info(">> getPageKeyTitle :: " + page.toString());
        model.addAttribute("page", page);
        return "page/page";
    }

    @GetMapping("/pages/{pageId}")
    public String getPageId(Model model, @PathVariable long pageId) {
        var page = pageRepo.findById(pageId).get();
        model.addAttribute("page", page);
        model.addAttribute("comments", commentRepo.findCommentByParentId(pageId));
        return "page/page";
    }

    @GetMapping("editpage/{pageId}")
    public String editPage(@PathVariable long pageId, Model model) {
        Page page = pageRepo.findById(pageId).get();
        model.addAttribute("page", page);
        model.addAttribute("pageId", pageId);
        return "page/edit-page";
    }

    @GetMapping("/pages/all")
    public String getPages(Model model) {
        model.addAttribute("pages", pageRepo.findAll());
        return "page/pages";
    }

}
