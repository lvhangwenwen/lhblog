package com.gdou.article.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "category")
public class Category {


    /**
     * 分类id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long CategoryId;

    /**
     * 分类米高程
     */
    private String CategoryName;

    /**
     * 该分类的博客数量
     */
    private Long blogNum;
}
