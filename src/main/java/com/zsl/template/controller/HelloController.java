package com.zsl.template.controller;

import com.zsl.template.common.AjaxResponse;
import com.zsl.template.entity.PersonInfo;
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
public class HelloController {
    @GetMapping("hello")
    public String hello(String name) {
        return "hello , " + name;
    }

    @GetMapping("/person")
    public AjaxResponse getPerson() {
        return AjaxResponse.success(PersonInfo.builder().age(18).name("zsl").build());
    }
}
