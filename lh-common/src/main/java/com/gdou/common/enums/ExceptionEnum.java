package com.gdou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum  ExceptionEnum {

    USER_NOT_FOUND(9995,"用户为空"),
    ARTICLE_NOT_FOUND(9991,"文章未找到"),
    CATEGORYS_NOT_FOUND(9992,"文章分类没有查询到"),
    FILE_TYPE_ERROR(9993,"文件类型错误" ),
    UPLOAD_ERROR(9994,"上传失败" ),
    USER_ALREADY_EXIST(9996,"用户已经存在"), INVALID_USERNAME_PASSWORD(9997,"账号有误" ), UN_AUTHREAED(9998, "未授权");
    private int     code;
    private String Msg;
}
