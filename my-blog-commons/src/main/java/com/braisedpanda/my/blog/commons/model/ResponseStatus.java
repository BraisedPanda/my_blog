package com.braisedpanda.my.blog.commons.model;

import lombok.Data;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-24 09:08
 **/
@Data
public class ResponseStatus<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseStatus(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResponseStatus(String message) {

        this.message = message;
    }


    /** 
    * @Description: 返回成功
    * @Param: 
    * @Date: 2019/12/24 0024 
    */

    public static <T> ResponseStatus<T> success (T data){
        return new ResponseStatus<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> ResponseStatus<T> success (T data, String message){
        return new ResponseStatus<T>(ResultCode.SUCCESS.getCode(), message, data);
    }
    public static <T> ResponseStatus<T> success (String message){
        return new ResponseStatus<T>(ResultCode.SUCCESS.getCode(), message);
    }
    public static <T> ResponseStatus<T> success (){
        return new ResponseStatus<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    /**
     * @Description: 返回失败
     * @Param:
     * @Date: 2019/12/24 0024
     */
    public static <T> ResponseStatus<T> FAILD (T data){
        return new ResponseStatus<T>(ResultCode.FAILD.getCode(), ResultCode.FAILD.getMessage(), data);
    }

    public static <T> ResponseStatus<T> FAILD (T data, String message){
        return new ResponseStatus<T>(ResultCode.FAILD.getCode(), message, data);
    }
    public static <T> ResponseStatus<T> FAILD ( String message){
        return new ResponseStatus<T>(ResultCode.FAILD.getCode(), message);
    }
    public static <T> ResponseStatus<T> FAILD (){
        return new ResponseStatus<T>(ResultCode.FAILD.getCode(), ResultCode.FAILD.getMessage());
    }


}
