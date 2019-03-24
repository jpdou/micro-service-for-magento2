package com.jpdou.m2review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TemplateController {

    @GetMapping("/template")
    public String index(@RequestParam(name="path") String path) {
        return path;
    }
}
