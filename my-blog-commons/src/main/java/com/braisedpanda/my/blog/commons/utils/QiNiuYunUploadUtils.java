package com.braisedpanda.my.blog.commons.utils;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.util.StringUtils;

public class QiNiuYunUploadUtils {

    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "c0a676i2zWaVlx3OJg2bwV4gEFVkDKGqE0t7D4Ve";
    private static final String SECRET_KEY = "qyAH45szZmqNPn2cwL4fgoFJVzWN3vRhOoRXts_j";

    // 要上传的空间
    private static final String bucketname = "braisedpanda-images";

    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //图面的URL地址
    private static final String DOMAIN = "images.braisedpanda.club";
    //自定义样式
    private static final String style =
            "imageView2/1/w/500/h/300/q/75|watermark/2/text/YnJhaXNlZHBhbmRh/font/Z2FicmlvbGE=/fontsize/400/fill/I0ZFRkJGQg==/dissolve/100/gravity/SouthEast/dx/10/dy/10|imageslim";

    public String getUpToken() {
        return auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
    }
    // 普通上传
    public String upload(String filePath, String fileName) throws IOException {
        // 创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            // 调用put方法上传
            String token = auth.uploadToken(bucketname);
            if(StringUtils.isEmpty(token)) {

                return "未获取到token，请重试！";
            }
            Response res = uploadManager.put(filePath, fileName, token);


            if (res.isOK()) {
                Ret ret = res.jsonToObject(Ret.class);
                //如果不需要对图片进行样式处理，则使用以下方式即可
                //return DOMAIN + ret.key;
                return DOMAIN + ret.key + "?" + style;
            }
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息

            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                // ignore
            }
        }
        return null;
    }


    //base64方式上传
    public String put64image(byte[] base64, String key) throws Exception{
        String file64 = Base64.encodeToString(base64, 0);
        Integer l = base64.length;
        //地区不同，url前面的域名也不相同
        String url = "http://upload-z2.qiniup.com/putb64/" + l + "/key/"+ UrlSafeBase64.encodeToString(key);

        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        //System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();

        //如果不需要添加图片样式，使用以下方式
        return "http://"+DOMAIN +"/"+ key;
//        return "http://"+DOMAIN +"/"+ key+ "?" + style;
    }


    // 普通删除(暂未使用以下方法，未测试)
    public void delete(String key) throws IOException {
        // 实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth);
        // 此处的33是去掉：http://ongsua0j7.bkt.clouddn.com/,剩下的key就是图片在七牛云的名称
        key = key.substring(33);
        try {
            // 调用delete方法移动文件
            bucketManager.delete(bucketname, key);
        } catch (QiniuException e) {
            // 捕获异常信息
            Response r = e.response;

        }
    }

    class Ret {
        public long fsize;
        public String key;
        public String hash;
        public int width;
        public int height;
    }
}