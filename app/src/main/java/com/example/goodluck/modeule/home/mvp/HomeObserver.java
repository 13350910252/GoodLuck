package com.example.goodluck.modeule.home.mvp;

import com.example.goodluck.mvp.CBaseView;
import com.example.goodluck.web.retrofit.BaseObserver;
import com.example.goodluck.web.retrofit.BaseRetrofitData;

public abstract   class HomeObserver extends BaseObserver<BaseRetrofitData> {

    public HomeObserver(CBaseView view) {
        super(view);
    }
}
