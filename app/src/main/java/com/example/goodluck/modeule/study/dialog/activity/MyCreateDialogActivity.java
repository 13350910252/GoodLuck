package com.example.goodluck.modeule.study.dialog.activity;

import android.os.Bundle;
import android.view.View;

import com.example.goodluck.R;
import com.example.goodluck.base.activity.AppBaseActivity;
import com.example.goodluck.databinding.ActivityMyCreateDialogBinding;
import com.example.goodluck.utils.StatusBarUtil;

/**
 * 本类实现的弹窗主要是为了解决弹窗弹出时，界面所有部分都会出现阴影。
 * 效果：在固定位置以下实现阴影效果，上边则不再有阴影效果
 */
public class MyCreateDialogActivity extends AppBaseActivity<ActivityMyCreateDialogBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        bindData();
    }
    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(mContext));
        binding.headerLayout.headerCenterTitle.setText(R.string.control_shadow_dialog);

        binding.btnDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view == binding.btnDialog) {
            binding.cdvTest.startAnimations();
        }
    }
}
