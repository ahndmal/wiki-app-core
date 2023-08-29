package com.anma.conflappcore.contr;

import com.anma.conflappcore.repo.PageRepo;
import com.anma.conflappcore.repo.SpaceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/spaces")
public class SpaceController {
    private final Logger LOG = LoggerFactory.getLogger(SpaceController.class);
    private final SpaceRepo spaceRepo;
    private final PageRepo pageRepo;

    @Autowired
    public SpaceController(SpaceRepo spaceRepo, PageRepo pageRepo) {
        this.spaceRepo = spaceRepo;
        this.pageRepo = pageRepo;
    }

    @GetMapping
    public String spaces(Model model) {
        model.addAttribute("spaces", spaceRepo.findAll());
        return "space/spaces";
    }

    @GetMapping("/{key}")
    public String getSpace(@PathVariable String key, Model model) {
        model.addAttribute("space", spaceRepo.findBySpaceKey(key));

        var spacePages = pageRepo.findBySpaceKeyQuery(key);

        LOG.info(">>> SPace page: serving " + spacePages.size() + " pages ");

        model.addAttribute("spacePages", spacePages);
        return "space/space";
    }

}
