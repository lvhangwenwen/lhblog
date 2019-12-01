package com.gdou.article.web;

import com.gdou.article.pojo.Article;
import com.gdou.article.pojo.Author;
import com.gdou.article.service.ArticleService;
import com.gdou.common.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveArticle(@RequestParam("userId") Long userId,
                                            @RequestParam("title") String title,
                                            @RequestParam("descript") String descript,
                                            @RequestParam("detail") String detail){



        return ResponseEntity.ok(articleService.saveArticle(userId,title,descript,detail)) ;
    }

    @GetMapping("/page")
    public ResponseEntity<PageResult<Article>>queryArticleByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "2") Integer rows,
            @RequestParam(value = "key", required = false) String key
    ){


        return ResponseEntity.ok(articleService.queryArticleByPage(page,rows,key));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer>queryArticleCount(){

        return ResponseEntity.ok(articleService.queryArticleCount());

    }
    @GetMapping("/{articleId}")
    public ResponseEntity<Article> queryArticleById(@PathVariable("articleId")Long articleId){

        return ResponseEntity.ok(articleService.queryArticleById(articleId));

    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> queryAuthorById(@PathVariable("id")Long authorId){

        return ResponseEntity.ok(articleService.queryAuthorById(authorId));

    }
}
