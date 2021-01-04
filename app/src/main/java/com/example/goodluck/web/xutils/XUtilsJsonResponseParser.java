package com.example.goodluck.web.xutils;

import com.google.gson.Gson;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;

public class XUtilsJsonResponseParser implements ResponseParser<String> {
    /**
     * 转换result为resultType类型的对象
     *
     * @param resultType  返回值类型(可能带有泛型信息)
     * @param resultClass 返回值类型
     * @param result      网络返回数据(支持String, byte[], JSONObject, JSONArray, InputStream)
     * @return 请求结果, 类型为resultType
     */
    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        Gson gson = new Gson();
        XUtilsBaseResp baseResp = gson.fromJson(result, XUtilsBaseResp.class);
        return baseResp;
    }

    @Override
    public void beforeRequest(UriRequest request) throws Throwable {

    }

    @Override
    public void afterRequest(UriRequest request) throws Throwable {

    }
}
