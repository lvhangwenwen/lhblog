package com.gdou.search.mq;

import com.gdou.search.service.SearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchListen {

    @Autowired
    private SearchService searchService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name="search.article.insert.queue",durable = "true"),
            exchange = @Exchange(name="lh.article.exchange",type = ExchangeTypes.TOPIC),
            key = {"article.insert","article.update"}
    ))
    public void listenInsertOrUpdate(Long articleId){
        if (articleId == null){
            return;
        }
        //处理消息,对索引库新增或者修改
        searchService.createOrUpdateIndex(articleId);
    }
}
