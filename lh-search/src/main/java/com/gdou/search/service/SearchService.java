package com.gdou.search.service;

import com.gdou.article.pojo.Article;
import com.gdou.common.vo.PageResult;
import com.gdou.search.client.ArticleClient;
import com.gdou.search.pojo.EsArticle;
import com.gdou.search.pojo.SearchRequest;
import com.gdou.search.pojo.SearchResult;
import com.gdou.search.repositoty.ArticleRepository;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private ArticleRepository repository;
    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ArticleClient articleClient;

    public EsArticle buildGoods(Article article) {
        EsArticle esArticle = new EsArticle();

        esArticle.setAll(article.getArticleTitle()+" "+article.getArticleTabloid()+" "+article.getArticleContent());
        esArticle.setArticle_tabloid(article.getArticleTabloid());
        esArticle.setArticle_tags(article.getArticleTags());
        esArticle.setArticleId(article.getArticleId());
        esArticle.setArticleTitle(article.getArticleTitle());
        esArticle.setAuthor(article.getAuthor_name());
        esArticle.setLikes(article.getLikes());
        esArticle.setPublishTime(article.getPublishDate());
        esArticle.setUserImg(article.getAuthor().getUserImg());
        return esArticle;
    }

    public PageResult<EsArticle> search(SearchRequest request) {
        String key= request.getKey();
        if (StringUtils.isBlank(key)){
            return null;
        }
        int page = request.getPage()-1;
        int size = request.getSize();

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withPageable(PageRequest.of(page,size));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder.should(QueryBuilders.matchQuery("all",request.getKey()));
        boolQueryBuilder.should(QueryBuilders.matchQuery("articleTitle",request.getKey()));
        boolQueryBuilder.should(QueryBuilders.matchQuery("article_tabloid",request.getKey()));
        boolQueryBuilder.should(QueryBuilders.matchQuery("author",request.getKey()));

        QueryBuilder basicQuery = boolQueryBuilder;

        queryBuilder.withQuery(basicQuery);

        AggregatedPage<EsArticle> result = template.queryForPage(queryBuilder.build(), EsArticle.class);

        long total = result.getTotalElements();
        long totalPage= result.getTotalPages();

        List<EsArticle> content = result.getContent();
        return new SearchResult(total,totalPage,content);
    }

    public void createOrUpdateIndex(Long articleId) {

        Article article = articleClient.queryArticleById(articleId);

        EsArticle esArticle = buildGoods(article);

        repository.save(esArticle);
    }
}
