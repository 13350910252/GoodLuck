package com.example.goodluck.web.xutils;
/**
 * 请求参数基类
 */
public class XUtilsBaseReqParams {
    /**
     * 具体方法名
     */
    private String method;

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return this.method;
    }
}
