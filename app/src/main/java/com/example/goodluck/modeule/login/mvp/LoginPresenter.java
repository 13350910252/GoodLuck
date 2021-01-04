package com.example.goodluck.modeule.login.mvp;

import android.util.Log;

import com.example.goodluck.mvp.BasePresenter;
import com.example.goodluck.web.retrofit.BaseRetrofitData;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView baseView) {
        super(baseView);
    }
    public void accountLogin(String name, String pwd) {
        addDisposable(apiRetrofit.accountLogin(name, pwd), new LoginObserver(baseView) {
            @Override
            public void onSuccess(BaseRetrofitData o) {
                Log.d("abcd", "onSuccess: "+o);
                baseView.accountLoginSuccess();
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }
}
