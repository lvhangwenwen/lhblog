package com.gdou.article.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "author")
public class Author {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long userId;

    private String phone;

    private String username;

    private String password;

    private String gender;

    private String trueName;

    private String birthday;

    private String email;

    private String personalBrief;

    private String userImg;


}
