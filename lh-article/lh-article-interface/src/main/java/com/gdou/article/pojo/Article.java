package com.gdou.article.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name = "article")
public class Article {

    /**
     * 文章id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long articleId;

    /**
     * 文章作者
     */
    private Long userId;

    private String author_name;



    /**
     * 文章名
     */
    private String articleTitle;

    /**
     * 发布时间
     */
    private String publishDate;

    /**
     * 最后一次修改时间
     */
    private String updateDate;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章标签
     */
    private String articleTags;

    /**
     * 文章摘要
     */
    private String articleTabloid;
    /**
     * 喜欢
     */
    private Long likes;

    /**
     *
     * 分类id
     */
    private Long categoryId;

    @Transient
    private Author author;

}
