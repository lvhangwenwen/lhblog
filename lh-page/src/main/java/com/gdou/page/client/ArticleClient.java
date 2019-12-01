package com.gdou.page.client;

import com.gdou.article.api.ArticleApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("article-service")
public interface ArticleClient extends ArticleApi {
}
