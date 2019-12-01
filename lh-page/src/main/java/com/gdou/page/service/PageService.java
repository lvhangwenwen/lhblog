package com.gdou.page.service;


import com.gdou.article.pojo.Article;
import com.gdou.page.client.ArticleClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PageService {

    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private TemplateEngine templateEngine;
    public Map<String, Object> loadModel(Long articleId) {

        HashMap<String, Object> model = new HashMap<>();
        Article article = articleClient.queryArticleById(articleId);

        article.setAuthor(articleClient.queryAuthorById(article.getUserId()));


        model.put("author",article.getAuthor().getUsername());
        model.put("authorImg",article.getAuthor().getUserImg());
        model.put("detail",article.getArticleContent());
        model.put("likes",article.getLikes());
        model.put("title",article.getArticleTitle());
        model.put("time",article.getPublishDate());



        return model;
    }

    public void createHtml(Long articleId) {

            Context context=new Context();
            context.setVariables(loadModel(articleId));

            //"/opt/nginx/html/page"
            File dest=new File("G:\\util\\nginx-1.14.0\\html\\page",articleId+".html");

            if (dest.exists()){
                dest.delete();
            }

            try(PrintWriter writer= new PrintWriter(dest,"UTF-8")){

                templateEngine.process("blogDetailsPage",context,writer);

            }catch (Exception e){

              log.info("报错");

            }

    }
}
