package com.braisedpanda.my.blog.web.mapper;
import com.braisedpanda.my.blog.commons.model.po.Editor;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;


@org.apache.ibatis.annotations.Mapper
@Component
public interface EditMapper extends Mapper<Editor>{
}
