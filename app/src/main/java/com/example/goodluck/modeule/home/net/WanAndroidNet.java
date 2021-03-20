package com.example.goodluck.modeule.home.net;

import android.util.Log;

import com.example.goodluck.modeule.home.mvp.CommonViewInterface;
import com.example.goodluck.web.retrofit.ApiRetrofit;
import com.example.goodluck.web.retrofit.BaseObserver;
import com.example.goodluck.web.retrofit.BaseRetrofitData;
import com.example.goodluck.web.retrofit.BuildRetrofit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class WanAndroidNet {
    protected static ApiRetrofit apiRetrofit = BuildRetrofit.getInstance(BuildRetrofit.BASE_WAN_ANDROID_URL).getApiRetrofit();

    public static void getList(CommonViewInterface<BaseRetrofitData> commonViewInterface) {
        apiRetrofit.homArticleList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseRetrofitData>(commonViewInterface) {
                    @Override
                    public void onSuccess(BaseRetrofitData o) {
                        Log.d("abcd", "onSuccess: " + o);
                        commonViewInterface.onSuccess(o);
                    }

                    @Override
                    public void onError(String msg) {
                        commonViewInterface.onError(msg);
                    }
                });

    }
}
