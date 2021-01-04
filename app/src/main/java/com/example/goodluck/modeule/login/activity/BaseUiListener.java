package com.example.goodluck.modeule.login.activity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

public class BaseUiListener implements IUiListener {
    @Override
    public void onComplete(Object o) {
        JsonObject jsonObject = new JsonParser().parse(o.toString()).getAsJsonObject();
        doComplete(jsonObject);
    }

    protected void doComplete(JsonObject values) {
    }

    @Override
    public void onError(UiError e) {

    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onWarning(int i) {

    }
}
