package com.gdou.page;

import com.gdou.page.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    private PageService pageService;

    @Test
    public void  createHtml(){


        pageService.createHtml(1533196748L);
    }
}
