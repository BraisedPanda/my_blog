package com.braisedpanda.my.blog.commons.model.dto;

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
public class DiaryDto implements Serializable{
    private static final long serialVersionUID = -5914371001146806544L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "createTime")
    private String createTime;
    @Column(name = "content")
    private String content;

}
