package com.gdou.page.web;


import com.gdou.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private PageService pageService;
    @GetMapping("page/{id}.html")
    public String toArticlePage(@PathVariable("id")Long articleId, Model model){


        Map<String,Object>attributes=pageService.loadModel(articleId);

        model.addAllAttributes(attributes);
        return "blogDetailsPage";

    }
}
