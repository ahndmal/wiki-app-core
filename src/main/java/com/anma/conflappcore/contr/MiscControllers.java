package com.anma.conflappcore.contr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MiscControllers {

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}
