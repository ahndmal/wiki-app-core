package com.anma.conflappcore.contr;

import com.anma.conflappcore.repo.SpaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
