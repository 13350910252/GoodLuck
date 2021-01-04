package com.example.goodluck.modeule.login.mvp;

import com.example.goodluck.mvp.CBaseView;
import com.example.goodluck.web.retrofit.BaseObserver;
import com.example.goodluck.web.retrofit.BaseRetrofitData;

public abstract class LoginObserver extends BaseObserver<BaseRetrofitData> {
    public LoginObserver(CBaseView view) {
        super(view);
    }
}
