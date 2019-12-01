package com.gdou.page.mq;

import com.gdou.page.service.PageService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleListener {

    @Autowired
    private PageService pageService;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name="page.article.insert.queue",durable = "true"),
            exchange = @Exchange(name="lh.article.exchange",type = ExchangeTypes.TOPIC),
            key = {"article.insert","article.update"}
    ))
    public void listenInsertOrUpdate(Long articleId){
        if (articleId == null){
            return;
        }
        //处理消息,对静态页创建
        pageService.createHtml(articleId);



    }
}
