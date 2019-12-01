package com.gdou.search.repositoty;

import com.gdou.search.pojo.EsArticle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<EsArticle,Long> {
}
