package com.gdou.article.service;


import com.gdou.article.mapper.AuthorMapper;
import com.gdou.article.pojo.Author;
import com.gdou.article.utils.CodecUtils;
import com.gdou.common.enums.ExceptionEnum;
import com.gdou.common.exception.LhException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {


    @Autowired
    private AuthorMapper authorMapper;
    public void registry(Author author) {

        Author author1 = new Author();
        author1.setUsername(author.getUsername());
        Author author2 = authorMapper.selectOne(author1);

        if (author2!=null){
            throw new LhException(ExceptionEnum.USER_ALREADY_EXIST);
        }

        author.setPassword(CodecUtils.md5Hex(author.getPassword(),"yan"));

        authorMapper.insert(author);


    }

    public Author queryUserByUsernameAndPassword(String username, String password) {

        Author record = new Author();
        record.setUsername(username);
        Author user = authorMapper.selectOne(record);

        if (user == null){
            throw new LhException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }
        if (!StringUtils.equals(user.getPassword(),password)){
            throw new LhException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
        }
//        if (!StringUtils.equals(user.getPassword(), CodecUtils.md5Hex(password,"yan"))){
//            throw new LhException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
//        }
        return user;
    }
}
