package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marcin on 21.04.2016.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = {"/", "home"})
    public String Index(){
        return "index";
    }
}
