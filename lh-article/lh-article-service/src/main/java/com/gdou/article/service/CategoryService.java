package com.gdou.article.service;

import com.gdou.article.mapper.CategoryMapper;
import com.gdou.article.pojo.Category;
import com.gdou.common.enums.ExceptionEnum;
import com.gdou.common.exception.LhException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> findAllCategorys() {

        List<Category> categories = categoryMapper.selectAll();

        if (CollectionUtils.isEmpty(categories)){
            throw new LhException(ExceptionEnum.CATEGORYS_NOT_FOUND);
        }

        return categories;
    }
}
