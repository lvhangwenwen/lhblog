package com.gdou.auth.web;

import com.gdou.auth.config.JwtProperties;
import com.gdou.auth.entiy.UserInfo;
import com.gdou.auth.service.AuthService;
import com.gdou.auth.utils.JwtUtils;
import com.gdou.common.enums.ExceptionEnum;
import com.gdou.common.exception.LhException;
import com.gdou.common.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private AuthService authService;
    @Value("${lh.jwt.cookieName}")
    private String cookieName;
    @Autowired
    private JwtProperties prop;


    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestParam("username")String username
    , @RequestParam("password")String password
    , HttpServletResponse response, HttpServletRequest request){

        //登陆
        String token = authService.login(username, password);
        //写入cookie
        CookieUtils.newBuilder(response).httpOnly().request(request).build(cookieName,token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("verify")
    public ResponseEntity<UserInfo> verify(@CookieValue("LY_TOKEN")String token , HttpServletResponse response, HttpServletRequest request){

//        if (StringUtils.isEmpty(token)) {
//            //token是空的
//            throw new LyException(ExceptionEnum.UN_AUTHREAED);
//        }
        try {
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            //刷新token
            String newtok = JwtUtils.generateToken(userInfo, prop.getPrivateKey(), prop.getExpire());
            //写回去
            CookieUtils.newBuilder(response).httpOnly().request(request).build(cookieName,newtok);
            return ResponseEntity.ok(userInfo);
        }catch (Exception e){

            throw new LhException(ExceptionEnum.UN_AUTHREAED);
        }

    }

}
