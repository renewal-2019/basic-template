package com.zsl.template.controller;

import com.zsl.template.common.AjaxResponse;
import com.zsl.template.entity.PersonInfo;
import com.zsl.template.service.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Hello
 *
 * @author swiftzsl
 * @date 2021/7/25 10:42
 */
@RestController
@RequestMapping("/test")
public class HelloController {
    @Resource
    private PersonService personService;

    @GetMapping("hello")
    public String hello(String name) {
        return "hello , " + name;
    }

    @GetMapping("/person")
    public AjaxResponse getPerson() {
        return AjaxResponse.success(personService.getPersonInfo());
    }

    @PostMapping("/save")
    public AjaxResponse savePerson(@RequestBody PersonInfo personInfo){
        return AjaxResponse.success(personInfo);
    }
}
