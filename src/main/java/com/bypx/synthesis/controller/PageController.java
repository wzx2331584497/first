package com.bypx.synthesis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller("page")

public class PageController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping("user")
    public String user() {
        return "user";
    }

    @RequestMapping("text2")
    public String text2() {
        return "text2";
    }
}

