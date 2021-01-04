package com.example.goodluck.base.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.example.goodluck.mvp.BasePresenter;
import com.example.goodluck.mvp.CBaseView;
import com.example.goodluck.web.retrofit.BaseRetrofitData;

public abstract class PresenterBaseActivity<T extends ViewBinding,P extends BasePresenter> extends AppBaseActivity<T> implements CBaseView {
    private P presenter;

    protected abstract P createPresenter();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onErrorCode(BaseRetrofitData model) {

    }
}
