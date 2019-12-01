package com.gdou.search.web;

import com.gdou.common.vo.PageResult;
import com.gdou.search.pojo.EsArticle;
import com.gdou.search.pojo.SearchRequest;
import com.gdou.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping("search")
    public ResponseEntity<PageResult<EsArticle>>search(@RequestBody SearchRequest request){


        return ResponseEntity.ok(searchService.search(request));
    }


}
