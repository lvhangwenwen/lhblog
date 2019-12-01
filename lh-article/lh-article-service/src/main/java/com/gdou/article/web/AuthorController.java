package com.gdou.article.web;


import com.gdou.article.pojo.Author;
import com.gdou.article.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("registry")
    public ResponseEntity<Void> registry(@RequestBody Author author){

        authorService.registry(author);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/query")
    public ResponseEntity<Author>queryUserByUsernameAndPassword(
            @RequestParam("username")String username,
            @RequestParam("password")String password
    ){

        return ResponseEntity.ok(authorService.queryUserByUsernameAndPassword(username,password));
    }
}
