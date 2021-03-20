package com.example.goodluck.modeule.home.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodluck.base.fragment.AppBaseFragment;
import com.example.goodluck.databinding.FragmentWanAndroidBinding;
import com.example.goodluck.modeule.home.adapter.WanAndroidAdapter;
import com.example.goodluck.modeule.home.entity.PagingContentData;
import com.example.goodluck.modeule.home.entity.WanAndroidHomeEntity;
import com.example.goodluck.modeule.home.mvp.CommonViewInterface;
import com.example.goodluck.modeule.home.net.WanAndroidNet;
import com.example.goodluck.web.retrofit.BaseRetrofitData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class WanAndroidFragment extends AppBaseFragment<FragmentWanAndroidBinding> implements CommonViewInterface<BaseRetrofitData> {
    WanAndroidAdapter androidAdapter;
    ArrayList<WanAndroidHomeEntity> list;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        bindData();
    }

    //    ArrayList
    @Override
    protected void initViews(View view) {
        initRecyclerView();
    }

    @Override
    protected void bindData() {
        super.bindData();
        WanAndroidNet.getList(this);
    }

    private void initRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        binding.rvList.setLayoutManager(llm);
        if (list == null) {
            list = new ArrayList<>();
        }
        androidAdapter = new WanAndroidAdapter(list);
        binding.rvList.setAdapter(androidAdapter);
    }

    @Override
    public void onSuccess(BaseRetrofitData o) {
        Gson gson = new Gson();
        PagingContentData contentData = gson.fromJson(o.getData(), PagingContentData.class);

        Type listType = new TypeToken<ArrayList<WanAndroidHomeEntity>>() {
        }.getType();
        ArrayList<WanAndroidHomeEntity> arrayList = new Gson().fromJson(contentData.getDatas(), listType);
        list.addAll(arrayList);
        androidAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {

    }
}
