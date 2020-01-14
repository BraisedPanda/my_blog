package com.braisedpanda.my.blog.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.braisedpanda.my.blog.commons.model.vo.ImageInfo;
import com.braisedpanda.my.blog.commons.utils.DateUtils;
import com.braisedpanda.my.blog.commons.utils.QiNiuYunUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-27 11:53
 **/
@RestController
@RequestMapping("/images")
@Api(tags = "图片处理类",description = "实现图片上传等功能")
public class ImageController {
    @ApiOperation("上传图片到七牛云存储空间上")
    @PostMapping("/upload")
    public String  upload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile image) throws Exception{

        int index = image.getOriginalFilename().indexOf(".");
        int last = image.getOriginalFilename().lastIndexOf(".");
        int length = image.getOriginalFilename().length();
        QiNiuYunUploadUtils upload = new QiNiuYunUploadUtils();
        String currentDate = DateUtils.currentStandardDate();
        String imageName = image.getOriginalFilename().substring(0,index)+"-"+currentDate+image.getOriginalFilename().substring(last,length);
        byte[] bytes = image.getBytes();
        String url = upload.put64image(bytes,imageName);
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setSuccess(1);
        imageInfo.setMessage("上传成功");
        imageInfo.setUrl(url);
        String result = JSONObject.toJSONString(imageInfo);
        return result;

    }

}
