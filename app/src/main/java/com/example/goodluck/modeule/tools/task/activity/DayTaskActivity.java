package com.example.goodluck.modeule.tools.task.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodluck.R;
import com.example.goodluck.base.activity.AppBaseActivity;
import com.example.goodluck.databinding.ActivityDayTaskBinding;
import com.example.goodluck.modeule.tools.task.adapter.DayTasKAdapter;
import com.example.goodluck.modeule.tools.task.entity.TaskEntity;
import com.example.goodluck.modeule.tools.task.mvp.TaskCVIew;
import com.example.goodluck.utils.GreenDaoUtils;
import com.example.goodluck.utils.StatusBarUtil;
import com.example.goodluck.utils.ThreadPool;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

public class DayTaskActivity extends AppBaseActivity<ActivityDayTaskBinding> implements TaskCVIew {
    List<TaskEntity> mList = new ArrayList<>();
    DayTasKAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        bindData();
    }

    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(mContext));
        binding.headerLayout.headerCenterTitle.setText(R.string.day_task);
        binding.tvAddDayTask.setOnClickListener(this);
        initRecycleView();
        initLayout();
    }

    @Override
    protected void bindData() {
        super.bindData();
        ThreadPool.getInstance().addTask(new Runnable() {
            @Override
            public void run() {
                List<TaskEntity> list = GreenDaoUtils.getInstance(mContext).querySQLiteForSize(TaskEntity.class, 0);
                mList.addAll(list);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecycleView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.rvList.setLayoutManager(gridLayoutManager);

        adapter = new DayTasKAdapter(mList);
        binding.rvList.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (isClick) {
            return;
        }
        isClick = true;
        new Handler().postDelayed(runnable, 500);
        if (view == binding.tvAddDayTask) {
            startActivity(new Intent(mContext, AddTaskActivity.class));
        }
    }

    int yOff = 0;
    int d = 0;

    public void initLayout() {
        binding.mCollapsingToolbarLayout.post(new Runnable() {
            @Override
            public void run() {
                binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (d == 0) {
                            d = binding.mCollapsingToolbarLayout.getMeasuredHeight() - binding.headerLayout.getRoot().getHeight();
                        }
                        if (yOff <= 0) {
                            yOff = binding.mCollapsingToolbarLayout.getMeasuredHeight() - binding.headerLayout.getRoot().getHeight();
                        }
                        Log.d(TAG, "verticalOffset: " + verticalOffset);
                        float rate = Math.abs(verticalOffset * 1f / yOff);
                        Log.d(TAG, "onOffsetChanged: " + rate * 255);
                        Log.d(TAG, "rate: " + rate);
                        binding.headerLayout.getRoot().getBackground().setAlpha((int) (rate * 255));
                        if (Math.abs(verticalOffset) >= yOff) {
                            Log.d(TAG, "onOffsetChanged: ");
                            binding.headerLayout.getRoot().getBackground().setAlpha(255);
                        }
                    }
                });
            }
        });
    }
}
