package com.example.goodluck.modeule.home.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.goodluck.base.fragment.PresenterBaseFragment;
import com.example.goodluck.databinding.FragmentHomePageBinding;
import com.example.goodluck.modeule.home.adapter.HomeBannerAdapter;
import com.example.goodluck.modeule.home.entity.BannerEntity;
import com.example.goodluck.modeule.home.mvp.HomePresenter;
import com.example.goodluck.modeule.home.mvp.HomeView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends PresenterBaseFragment<FragmentHomePageBinding, HomePresenter> implements HomeView {
    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        bindData();
    }

    @Override
    protected void initViews(View view) {
        homeBannerAdapter = new HomeBannerAdapter(listBanner, getContext());
        binding.banner.setAdapter(homeBannerAdapter).addBannerLifecycleObserver(this).setIndicator(new CircleIndicator(getContext()));
        binding.banner.setOnBannerListener((OnBannerListener<BannerEntity>) (data, position) -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(data.getUrl()));
            startActivity(intent);
        });
        binding.vsHomeMajorButton.ctvCalendar.setOnClickListener(this);
    }

    @Override
    protected void bindData() {
        super.bindData();
        presenter.getBannerList();
    }

    private List<BannerEntity> listBanner;
    private HomeBannerAdapter homeBannerAdapter;

    @Override
    public <T> void showBanner(T data) {
        JsonElement jsonElement = (JsonElement) data;
        if (jsonElement.isJsonNull()){
            return;
        }
        Type type = new TypeToken<ArrayList<BannerEntity>>() {
        }.getType();
        listBanner = new Gson().fromJson(jsonElement, type);

        homeBannerAdapter.setDatas(listBanner);
        homeBannerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == binding.vsHomeMajorButton.ctvCalendar){

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.banner.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.banner.destroy();
    }
}
