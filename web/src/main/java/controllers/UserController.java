package controllers;

import facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Václav Zouzalík
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String findAll(Model model)
    {
        model.addAttribute("users", userFacade.getAllUsers());
        return "user/list";
    }
}
