package com.gdou.search.pojo;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "articles",type = "docs",shards = 1,replicas = 0)
public class EsArticle {

    @Id
    private Long ArticleId;

    //所有需要被查询的信息，包括标题，描述，内容
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String all;

    @Field(type = FieldType.Keyword)
    private String author;


    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String articleTitle;

    private String publishTime;

    private String article_tags;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String article_tabloid;

    private String userImg;


    private Long likes;






}
