package com.learning.eCommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private static final Logger log =
            LoggerFactory.getLogger(MainController.class);

    public MainController() {
        System.out.println("main controller Object created..");
    }

    @GetMapping("/gotoNextPage")
    public ModelAndView gotoNextPage(ModelAndView modelAndView) {

        modelAndView.setViewName("nextPage.html");
        return modelAndView;
    }

    @GetMapping("/back")
    public String back() {
        log.info("back called");
        return "index.html";
    }

}
