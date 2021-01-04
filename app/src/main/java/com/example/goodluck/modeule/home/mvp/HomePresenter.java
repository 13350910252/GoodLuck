package com.example.goodluck.modeule.home.mvp;

import com.example.goodluck.mvp.BasePresenter;
import com.example.goodluck.web.retrofit.BaseRetrofitData;

public class HomePresenter extends BasePresenter<HomeView> {
    public HomePresenter(HomeView baseView) {
        super(baseView);
    }

    public void getBannerList() {
        addDisposable(apiRetrofit.getBannerList(), new HomeObserver(baseView) {

            @Override
            public void onSuccess(BaseRetrofitData o) {
                baseView.showBanner(o.getData());
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
