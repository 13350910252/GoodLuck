package com.example.goodluck.modeule.tools.task.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodluck.R;
import com.example.goodluck.base.activity.AppBaseActivity;
import com.example.goodluck.databinding.ActivityDayTaskBinding;
import com.example.goodluck.modeule.tools.task.adapter.DayTasKAdapter;
import com.example.goodluck.modeule.tools.task.entity.TaskEntity;
import com.example.goodluck.modeule.tools.task.mvp.TaskCVIew;
import com.example.goodluck.utils.AppUtils;
import com.example.goodluck.utils.Constant;
import com.example.goodluck.utils.GreenDaoUtils;
import com.example.goodluck.utils.StatusBarUtil;
import com.example.goodluck.utils.ThreadPool;
import com.google.android.material.appbar.AppBarLayout;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
        binding.headerLayout.viewStatus.setBackgroundColor(ContextCompat.getColor(mContext,R.color.red));
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
        /**
         * 设置间距
         */
        int screenWidth = (int) AppUtils.getWidthPixels(mContext); //屏幕宽度
        int itemWidth = (int) AppUtils.dpToPx(mContext, 180); //每个item的宽度
        binding.rvList.addItemDecoration(new SpaceItemDecoration(mContext, itemWidth));

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
        Intent intent = null;
        if (view == binding.tvAddDayTask) {
            intent = new Intent(mContext, AddTaskActivity.class);
            startActivityForResult(intent, Constant.DayTaskActivityCode.REQUEST_CODE);
        }
    }

    int yOff = 0;
    int d = 0;

    public void initLayout() {
        binding.mCollapsingToolbarLayout.post(() -> binding.appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
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
            binding.headerLayout.getRoot().getBackground().setAlpha((int) ((1 - rate) * 255));
            binding.headerLayout.viewStatus.getBackground().setAlpha((int) (rate * 255));
            if (Math.abs(verticalOffset) >= yOff) {
                Log.d(TAG, "onOffsetChanged: ");
                binding.headerLayout.getRoot().getBackground().setAlpha(0);
                binding.headerLayout.viewStatus.getBackground().setAlpha(255);
            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constant.DayTaskActivityCode.REQUEST_CODE == requestCode) {
            List<TaskEntity> list = GreenDaoUtils.getInstance(mContext).querySQLiteForSize(TaskEntity.class, 0);
            mList.clear();
            mList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int space;  //位移间距
        private Context context;

        public SpaceItemDecoration(Context context, int space) {
            this.space = space;
            this.context = context;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) % 2 == 1) {
                outRect.left = (int) AppUtils.dpToPx(context, 10);
                outRect.right = (int) AppUtils.dpToPx(context, 20);
                outRect.top = (int) AppUtils.dpToPx(context, 12);
            } else {
                outRect.left = (int) AppUtils.dpToPx(context, 20);
                outRect.right = (int) AppUtils.dpToPx(context, 10);
                outRect.top = (int) AppUtils.dpToPx(context, 12);
            }
        }

    }


}
