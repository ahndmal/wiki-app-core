package com.anma.conflappcore.contr;

import com.anma.conflappcore.repo.SpaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/spaces")
public class SpaceController {

    private final SpaceRepo spaceRepo;

    @Autowired
    public SpaceController(SpaceRepo spaceRepo) {
        this.spaceRepo = spaceRepo;
    }

    @GetMapping
    public String spaces(Model model) {
        model.addAttribute("spaces", spaceRepo.findAll());
        return "spaces";
    }

    @GetMapping("/{key}")
    public String getSpace(@PathVariable String key, Model model) {
        model.addAttribute("space", spaceRepo.findBySpaceKey(key));
        return "space";
    }

}
