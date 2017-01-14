package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pato on 15.12.2016.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        return "home";
    }

    @GetMapping("/accessdenied")
    public String accessdenied(Model model){
        return "accessdenied";
    }
}
