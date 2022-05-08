package com.anma.conflappcore.res;

import com.anma.conflappcore.repo.PageRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final PageRepo pageRepo;

    public HomeController(PageRepo pageRepo) {
        this.pageRepo = pageRepo;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/pages/{spaceKey}/{pageTitle}")
    public String getPage(Model model, @PathVariable String spaceKey, @PathVariable String pageTitle) {

        var page = pageRepo.findByTitleAndSpace_Key(pageTitle, spaceKey);
        model.addAttribute("page", page);

        return "page";
    }
}
