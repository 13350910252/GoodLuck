package com.example.goodluck.web.retrofit;

import com.example.goodluck.mvp.CBaseView;

public class TET extends BaseObserver<BaseRetrofitData> {

    public TET(CBaseView view) {
        super(view);
    }

    @Override
    public void onSuccess(BaseRetrofitData o) {

    }

    @Override
    public void onError(String msg) {

    }
}
