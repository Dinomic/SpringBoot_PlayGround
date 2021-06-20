package Personal.Dinomic.SpringBoot_PlayGround.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {

    @RequestMapping("/")
    public String home(Model model){
        return "home";
    }
}
