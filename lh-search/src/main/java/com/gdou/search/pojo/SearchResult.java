package com.gdou.search.pojo;

import com.gdou.common.vo.PageResult;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SearchResult extends PageResult<EsArticle> {



    public SearchResult() {

    }


    public SearchResult(Long total, Long totalPage, List<EsArticle> items ) {
        super(total, totalPage, items);

    }
}