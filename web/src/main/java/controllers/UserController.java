package controllers;

import dto.UserDTO;
//import enums.Roles;
import facade.UserFacade;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by pato on 14.12.2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserFacade userFacade;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String findAll(Model model) {
        logger.info("Showing all users");
        model.addAttribute("users", userFacade.getAllUsers());
        return "user/list";
    }


}
