package com.learning.eCommerce.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpResponse;


@RestController
@RequestMapping("/api")
public class MainController {

    private static final Logger log =
            LoggerFactory.getLogger(MainController.class);

    public MainController() {
        System.out.println("main controller Object created..");
    }

    @GetMapping("/userRegister")
    public void  gotoNextPage( ) {
        log.info("go to called");

    }

    @GetMapping("/back")
    public String back() {
        log.info("back called");

        return "index.html";
    }

    @GetMapping("/id")
    public ResponseEntity<Integer> returnId(@RequestParam int id){
        log.info("entered id : {}",id);

        return ResponseEntity.ok(id);
    }

}
