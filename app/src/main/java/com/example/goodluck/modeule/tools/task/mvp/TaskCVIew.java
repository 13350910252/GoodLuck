package com.example.goodluck.modeule.tools.task.mvp;

import com.example.goodluck.mvp.CBaseView;

public interface TaskCVIew {
    public interface SQLiteCallBack {
        void onSqlSuccess(String result);

        void onSqlFail(String result);

    }

    public interface SQLiteCallBack2 {
        void onSuccess();

        void onFail();
    }
}
