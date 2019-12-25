package com.braisedpanda.my.blog.commons.model;


public enum ResultCode {
    SUCCESS(200,"操作成功"),
    FAILD(-1,"操作失败"),
    BAD_REQUEST(400,"错误请求"),
    NOT_FOUND(404,"请求或页面没找到"),
    INTERNET_SERVER_ERROR(500,"网络服务器错误")
    ;

    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
