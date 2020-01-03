package com.braisedpanda.my.blog.commons.model.dto;

import lombok.Data;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2020-01-03 10:59
 **/
@Data
public class PageInfo {

    private Integer pageTotal;    //总共多少页
    private Integer totalCount; //总记录数
    private Integer pageSize; //每页大小
    private Integer page;  //当前页

}
