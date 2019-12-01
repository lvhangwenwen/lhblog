package com.gdou.auth.service;

import com.gdou.article.pojo.Author;
import com.gdou.auth.client.UserClient;
import com.gdou.auth.config.JwtProperties;

import com.gdou.auth.entiy.UserInfo;
import com.gdou.auth.utils.JwtUtils;
import com.gdou.common.enums.ExceptionEnum;
import com.gdou.common.exception.LhException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthService {

    @Autowired
    private UserClient userClient;
    @Autowired
    private JwtProperties prop;

    public String login(String username, String password) {

        try {
            //校验
            Author user = userClient.queryUserByUsernameAndPassword(username,password);
            //判断
            if (user==null) {
                throw new LhException(ExceptionEnum.INVALID_USERNAME_PASSWORD);
            }

            String token = JwtUtils.generateToken(new UserInfo(user.getUserId(),username),prop.getPrivateKey(), prop.getExpire());

            //生成token

            return token;
        }catch (Exception e){

            log.error("[授权中心]用户名或者密码有误，用户名称{}",username);
            throw new LhException(ExceptionEnum.INVALID_USERNAME_PASSWORD);

        }

    }
}
