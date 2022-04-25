package com.zsl.template.controller;


import com.zsl.template.entity.Article;
import com.zsl.template.entity.PersonInfo;
import com.zsl.template.service.ArticleService;
import com.zsl.template.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author zsl
 * @since 2021-07-26
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
}
