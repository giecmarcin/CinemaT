package com.app.controllers;

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

    @RequestMapping(value = "/2")
    public String Index2(){
        return "index2";
    }
}
