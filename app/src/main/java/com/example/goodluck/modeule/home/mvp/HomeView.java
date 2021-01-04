package com.example.goodluck.modeule.home.mvp;

import com.example.goodluck.mvp.CBaseView;

public interface HomeView extends CBaseView {
   <T> void showBanner(T data);
}
