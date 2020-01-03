package com.braisedpanda.my.blog.commons.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: my-blog
 * @description: 日记类
 * @author: chenzhen
 * @create: 2019-12-30 16:27
 **/
@Data
@Table(name = "diary_preview")
public class DiaryWebDto implements Serializable{

    private static final long serialVersionUID = -5459823084061855369L;
    private Integer id;
    private String content;
    private String day;
    private String month;
    private String year;
    private String title;

}
