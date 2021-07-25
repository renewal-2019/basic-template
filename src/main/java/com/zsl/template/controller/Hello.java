package com.zsl.template.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello
 *
 * @author swiftzsl
 * @date 2021/7/25 10:42
 */
@RestController
@RequestMapping("/test")
public class Hello {
    @GetMapping("hello")
    public String hello(String name){
        return "hello , " + name;
    }
}
