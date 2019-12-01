package com.gdou.search;


import com.gdou.article.pojo.Article;
import com.gdou.common.vo.PageResult;
import com.gdou.search.client.ArticleClient;
import com.gdou.search.pojo.EsArticle;
import com.gdou.search.repositoty.ArticleRepository;
import com.gdou.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    private ElasticsearchTemplate template;
    @Autowired
    private ArticleClient articleClient;
    @Autowired
    private SearchService searchService;
    @Autowired
    private ArticleRepository repository;
    @Test
    public void testCreateIndex(){
        template.createIndex(EsArticle.class);
        template.putMapping(EsArticle.class);

    }

    @Test
    public void loadDate(){
        int page=1;
        int rows=20;
        int size=0;

        do {


            //查询文章
            PageResult<Article> result = articleClient.queryArticleByPage(page, rows, null);

            List<Article> articleList = result.getItems();
            if (CollectionUtils.isEmpty(articleList)){
                break;
            }
            //构成goods
            List<EsArticle> goodsList = articleList.stream()
                    .map(searchService::buildGoods).collect(Collectors.toList());

            //存入索引库

            repository.saveAll(goodsList);
            page++;
            size=articleList.size();
        }while (size!=0);
    }
}
