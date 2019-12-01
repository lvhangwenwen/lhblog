package com.gdou.article.api;

import com.gdou.article.pojo.Author;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
public interface AuthorApi {

    @GetMapping("/query")
   Author queryUserByUsernameAndPassword(
            @RequestParam("username")String username,
            @RequestParam("password")String password
    );
}
