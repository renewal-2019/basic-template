package com.zsl.template.service.impl;

import com.zsl.template.entity.Article;
import com.zsl.template.mapper.ArticleMapper;
import com.zsl.template.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author zsl
 * @since 2021-07-26
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
