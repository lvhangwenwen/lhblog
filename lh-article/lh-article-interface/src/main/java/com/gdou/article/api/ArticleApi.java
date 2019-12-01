package com.gdou.article.api;

import com.gdou.article.pojo.Article;
import com.gdou.article.pojo.Author;
import com.gdou.common.vo.PageResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping("/article")
public interface ArticleApi {

    @GetMapping("/page")
   PageResult<Article> queryArticleByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "2") Integer rows,
            @RequestParam(value = "key", required = false) String key);

    @GetMapping("/count")
    Integer queryArticleCount();

    @GetMapping("/{articleId}")
   Article queryArticleById(@PathVariable("articleId")Long articleId);

    @GetMapping("/author/{id}")
    Author queryAuthorById(@PathVariable("id")Long authorId);
}
