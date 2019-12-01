package com.gdou;

import com.gdou.article.pojo.Author;
import com.gdou.auth.client.UserClient;
import com.gdou.auth.entiy.UserInfo;
import com.gdou.auth.utils.JwtUtils;
import com.gdou.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.PrivateKey;
import java.security.PublicKey;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    UserClient userClient;


    private static final String pubKeyPath = "G:\\Program Files (x86)\\rsa.pub";

    private static final String priKeyPath = "G:\\Program Files (x86)\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @org.junit.Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @org.junit.Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @org.junit.Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU2NjgyMTM1NH0.SlmQOI4NBcpfJTsvYNPIK9hLA9Pp5Y0-lGOqLY9Z6vPQTY5gaKi-3W7lYT7TdnRUFPv409w4vP_RpSSt8Besj_yd-FVn7nVuIx6ST2M6yooVxhTOuo5h-ylfmk71MsEmkMojJsWu1pXG9xd7oe3S4o97YSwWy_zWHYoYSyzDoy0";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }

    @Test
    public void query(){

        Author author = userClient.queryUserByUsernameAndPassword("lvhang", "123456");
        System.out.println(author);
    }
}
