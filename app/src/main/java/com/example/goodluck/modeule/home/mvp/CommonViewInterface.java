package com.example.goodluck.modeule.home.mvp;

import com.example.goodluck.mvp.CBaseView;

public interface CommonViewInterface<T> extends CBaseView {
    void onSuccess(T o);

    void onError(String msg);
}
