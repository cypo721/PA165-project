package controllers;

import dto.UserDTO;
import facade.UserFacade;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * Created by marek
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes ) {
        UserDTO user = userFacade.findById(id);
        try{
            userFacade.deleteUser(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "User \"" + user.getEmail() + "\" cannot be deleted.");
            return "redirect:" + uriBuilder.path("/user/").toUriString();
        }
        logger.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "User \"" + user.getEmail() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/user/").toUriString();
    }

}
