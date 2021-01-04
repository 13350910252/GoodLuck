package com.example.goodluck.web.xutils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求响应数据data基类
 */
public class XUtilsDataResp {
    /**
     * 当前页
     */
    public int pageNo;
    /**
     * 每页数量
     */
    public int pageSize;
    /**
     * 总页数
     */
    public int pageTotal;
    /**
     * 总记录数
     */
    public int itemTotal;
    /**
     *
     */
    public int total;
    /**
     * 结果主体
     */
    public JsonElement root;

    public List list;

    public XUtilsDataResp() {
        list = new ArrayList();
    }

    public void setList(Type type) {
        Gson gson = new Gson();
        list = gson.fromJson(root, type);
    }
}
