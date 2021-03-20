package com.example.goodluck.modeule.home.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.goodluck.base.fragment.PresenterBaseFragment;
import com.example.goodluck.databinding.FragmentHomePageBinding;
import com.example.goodluck.modeule.home.adapter.HomeBannerAdapter;
import com.example.goodluck.modeule.home.calendar.activity.MyCalendarActivity;
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
import java.util.Arrays;
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

        initViewPager2();
    }

    @Override
    protected void bindData() {
        super.bindData();
        presenter.getBannerList();
    }

    @Override
    public void onResume() {
        super.onResume();
        Window.Callback callback = getActivity().getWindow().getCallback();
        if (callback == null){
            Log.d(TAG, "onResume:a ");
        }else {
            Log.d(TAG, "onResume:b ");
        }
    }

    List<Fragment> list;
    WanAndroidFragment wanAndroidFragment;

    private void initViewPager2() {
        wanAndroidFragment = new WanAndroidFragment();
        list = new ArrayList<>(Arrays.asList(wanAndroidFragment));
        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(getActivity());
        binding.viewPage2.setAdapter(adapter);
        binding.viewPage2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.viewPage2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });
        binding.viewPage2.setSaveEnabled(false);
    }

    private List<BannerEntity> listBanner;
    private HomeBannerAdapter homeBannerAdapter;

    @Override
    public <T> void showBanner(T data) {
        JsonElement jsonElement = (JsonElement) data;
        if (jsonElement.isJsonNull()) {
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
        Intent intent = new Intent();
        if (v == binding.vsHomeMajorButton.ctvCalendar) {
            intent.setClass(getContext(), MyCalendarActivity.class);
            startActivity(intent);
        }
    }

    class MyFragmentStateAdapter extends FragmentStateAdapter {
        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        public MyFragmentStateAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        public MyFragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return list.get(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
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
