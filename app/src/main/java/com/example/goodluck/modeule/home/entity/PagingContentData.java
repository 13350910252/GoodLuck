package com.example.goodluck.modeule.home.entity;

import com.google.gson.JsonElement;

public class PagingContentData {
   private int curPage;
   private JsonElement datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public JsonElement getDatas() {
        return datas;
    }

    public void setDatas(JsonElement datas) {
        this.datas = datas;
    }
}
