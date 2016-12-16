/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import facade.RevisionFacade;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eduard
 */
@Controller
@RequestMapping("/revision")
public class RevisionController {
 private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RevisionFacade revisionFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String findAll(Model model) {
        logger.info("Showing all additional services");
        model.addAttribute("revisions", revisionFacade.findAllRevisions());
        return "revision/list";
    }
}

