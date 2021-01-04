package com.example.goodluck.utils;

import java.util.List;

public interface CallBack {
    public interface SQLiteCallBack {
        void onSqlSuccess(String result);

        void onSqlFail(String result);
    }
}
