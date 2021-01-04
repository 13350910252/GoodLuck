package com.example.goodluck.mvp;

import com.example.goodluck.web.retrofit.ApiRetrofit;
import com.example.goodluck.web.retrofit.BaseObserver;
import com.example.goodluck.web.retrofit.BuildRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<V extends CBaseView> {

    private CompositeDisposable compositeDisposable;

    public V baseView;

    protected ApiRetrofit apiRetrofit = BuildRetrofit.getInstance(BuildRetrofit.BASE_WAN_ANDROID_URL).getApiRetrofit();

    public BasePresenter(V baseView) {

        this.baseView = baseView;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        baseView = null;
        removeDisposable();
    }

    /**
     * 返回 view
     *
     * @return
     */
    public V getBaseView() {
        return baseView;
    }

    public void addDisposable(Observable<?> observable, BaseObserver observer) {

        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        observable.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                compositeDisposable.add(disposable);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }

    public void removeDisposable() {
        if (compositeDisposable != null) {
            //主动解除订阅
            compositeDisposable.dispose();
        }
    }

}
