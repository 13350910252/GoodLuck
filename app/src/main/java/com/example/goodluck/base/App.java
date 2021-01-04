package com.example.goodluck.base;

import android.content.Context;

public class App extends BaseApplication{
    public static Context context = null;
    @Override
    public void onCreate() {
        super.onCreate();
        context = mContext;
    }
}
