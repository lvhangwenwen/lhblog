package com.gdou.article.service;

import com.gdou.article.mapper.ArticleMapper;
import com.gdou.article.mapper.AuthorMapper;
import com.gdou.article.pojo.Author;
import com.gdou.common.enums.ExceptionEnum;
import com.gdou.common.exception.LhException;
import com.gdou.common.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.gdou.article.pojo.Article;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public PageResult<Article> queryArticleByPage(Integer page, Integer rows, String key) {

        //mybatise分页助手
        PageHelper.startPage(page,rows);

        //以主题为例创建过滤条件模板
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();

        //判断是否有关键字
        //如果有的话就加上
        if (StringUtils.isNotEmpty(key)) {
            criteria.andLike("article_title","%"+key+"%");
        }

        //按照什么字段对搜索结果排序
        example.setOrderByClause("update_date DESC");

        //开始搜索
        List<Article> articles = articleMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(articles)){
            throw new LhException(ExceptionEnum.ARTICLE_NOT_FOUND);
        }

        Author author = new Author();
        for (Article article : articles) {
            author.setUserId(article.getUserId());
            article.setAuthor(authorMapper.selectOne(author));
        }
        PageInfo<Article> info = new PageInfo<>(articles);

        return new PageResult<>(info.getTotal(),articles);


    }

    public Integer queryArticleCount() {

        Article article = new Article();
        int count = articleMapper.selectCount(article);
        return count;
    }

    public Article queryArticleById(Long articleId) {


        Article article = new Article();
        article.setArticleId(articleId);

        Article article1 = articleMapper.selectOne(article);

        Author author = new Author();
        author.setUserId(article1.getUserId());
        article1.setAuthor(authorMapper.selectOne(author));

        if (article1==null){
            throw new LhException(ExceptionEnum.ARTICLE_NOT_FOUND);
        }


        return article1;
    }

    public Author queryAuthorById(Long authorId) {

        Author author = new Author();
        author.setUserId(authorId);
        Author author1 = authorMapper.selectOne(author);
        return author1;
    }

    public Boolean saveArticle(Long userId, String title, String descript, String detail) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Article article = new Article();
        Author author = new Author();
        author.setUserId(userId);
        Author author1 = authorMapper.selectOne(author);

        article.setArticleId(null);
        article.setUserId(userId);
        article.setArticleTitle(title);
        article.setArticleTabloid(descript);
        article.setArticleContent(detail);
        article.setPublishDate(simpleDateFormat.format(new Date()));
        article.setUpdateDate(simpleDateFormat.format(new Date()));
        article.setAuthor_name(author1.getUsername());
        article.setLikes(10086L);
        article.setArticleTags("空");

        int insert = articleMapper.insert(article);

        if (insert!=0){
            amqpTemplate.convertAndSend("article.insert",article.getArticleId());
            return true ;
        }



        return false;
    }
}
