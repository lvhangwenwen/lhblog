package com.gdou.auth.client;


import com.gdou.article.api.AuthorApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("article-service")
public interface UserClient extends AuthorApi {
}
