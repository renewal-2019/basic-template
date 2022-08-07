package com.zsl.template.apitemplates;

import com.zsl.template.entity.Article;
import com.zsl.template.entity.PersonInfo;
import com.zsl.template.service.ArticleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class OptionalUsage {
    @Resource
    private ArticleService articleService;

    // 生成optional
    public Optional<Article> getArticleById(Integer id) {
        Optional<Article> optionalArticle = Optional.ofNullable(articleService.getById(id));
        return optionalArticle;
    }

    // 获取optional值，没有则返回设定值
    public Article simpleGet(Integer id) {
        Optional<Article> articleById = getArticleById(id);
        return articleById.orElse(new Article());
    }

    // 获取optional值，没有则执行指定函数
    public Article getOrExecute(Integer id) {
        Optional<Article> articleById = getArticleById(id);
        return articleById.orElseGet(() -> articleService.getById(0));
    }

    // 获取optional值，没有则抛出异常
    public Article getOrThrow(Integer id) {
        Optional<Article> articleById = getArticleById(id);
        return articleById.orElseThrow(IllegalAccessError::new);
    }

    // 如果值存在，则调用函数，否则不会发生任何事
    public void AddOrNothing(List<Article> articleList, Integer id) {
        Optional<Article> articleById = getArticleById(id);
        // ifPresent没有返回值
        articleById.ifPresent(articleList::add);
    }

    // 如果值存在，则调用函数，否则不会发生任何事
    public void AddOrReturn(List<Article> articleList, Integer id) {
        Optional<Article> articleById = getArticleById(id);
        // map有返回值
        // result有三种情况：值为true或者false或者Optional为空
        Optional<Boolean> result = articleById.map(articleList::add);
    }

    // flatMap和map的区别在于对于Function参数的要求不同
    // map要求入参函数的返回值为T
    // flatMap要求的入参函数的返回值为Optional<T>
    public void flat(List<Article> articleList, Integer id) {
        Optional<Article> articleById = getArticleById(id);
        Optional<PersonInfo> personInfo = articleById.flatMap((article) -> getRelatedPerson(article));
    }

    public Optional<PersonInfo> getRelatedPerson(Article article) {
        // 根据文章的作者名获取作者的相关信息
        return Optional.of(new PersonInfo());
    }
}
