package com.example.goodluck.base.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.example.goodluck.mvp.BasePresenter;

public abstract class PresenterBaseFragment<T extends ViewBinding,P extends BasePresenter> extends AppBaseFragment<T>{
    protected P presenter;
    protected abstract P createPresenter();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}
