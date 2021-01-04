package com.example.goodluck.web.xutils;

import com.google.gson.JsonElement;

import org.xutils.http.annotation.HttpResponse;

/**
 * 请求响应数据基类
 */
@HttpResponse(parser = XUtilsJsonResponseParser.class)
public class XUtilsBaseResp {
    /**
     * 请求结果消息
     */
    public String msg;
    /**
     * 请求错误码
     */
    public String code;
    /**
     * 请求结果体
     */
    public JsonElement data;
}
