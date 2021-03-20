package com.example.goodluck.modeule.me.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodluck.base.activity.AppBaseActivity;
import com.example.goodluck.databinding.ActivitySettingBinding;
import com.example.goodluck.modeule.me.adapter.SettingListAdapter;
import com.example.goodluck.utils.StatusBarUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SettingActivity extends AppBaseActivity<ActivitySettingBinding> {
    SettingListAdapter adapter;
    HashMap<String, Integer> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        bindData();
    }

    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(mContext));
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(RecyclerView.VERTICAL);
        binding.rvList.setLayoutManager(llm);

        map = new HashMap<>();
        map.put("配置首页工具栏", 1);
        adapter = new SettingListAdapter(map, mContext);
        binding.rvList.setAdapter(adapter);

        initRecyclerView();
    }

    private void initRecyclerView() {

    }

    @Override
    protected void bindData() {
        super.bindData();
    }
}