package com.example.goodluck.modeule.study.dialog.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.goodluck.base.activity.AppBaseActivity;
import com.example.goodluck.databinding.ActivitySystemDialogBinding;
import com.example.goodluck.utils.StatusBarUtil;

import java.lang.reflect.Field;

public class SystemDialogActivity extends AppBaseActivity<ActivitySystemDialogBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        bindData();
    }
    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(this));
        binding.headerLayout.headerCenterTitle.setText("修改系统的dialog");
        binding.btnOne.setOnClickListener(this);
        binding.btnTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view == null)
            return;
        cancel();
        initDialog();
        show();
        if (view == binding.btnOne) {
            one();
        } else if (view == binding.btnTwo) {
            two();
        }
    }

    AlertDialog alertDialog;

    private void initDialog() {
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("测试")
                .setMessage("内容")
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
    }

    private void show() {
        if (alertDialog != null) {
            alertDialog.show();
            //主要是解决好像是9.0以后不居中的问题
            //放在show()之后，不然有些属性是没有效果的，比如height和width
            Window dialogWindow = alertDialog.getWindow();
            WindowManager m = dialogWindow.getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            // 设置宽度
            p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.95
            p.gravity = Gravity.CENTER;//设置位置
            //p.alpha = 0.8f;//设置透明度
            dialogWindow.setAttributes(p);
        }
    }

    private void cancel() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.cancel();
            alertDialog = null;
        }
    }

    private void one() {
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE);
    }

    /**
     * 可以更改内容和标题
     */
    private void two() {
        if (alertDialog == null) {
            return;
        }
        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(alertDialog);
            //mTitleView;mMessageView;mMessage;mTitle
//            Field[] fields = mAlertController.getClass().getDeclaredFields();
//            for (int i = 0; i < fields.length; i++) {
//                Log.d(TAG, "two: " + fields[i].getName());
//            }
            //androidx.appcompat.app.AlertDialog反射才能拿得到很多属性
            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            mMessage.setAccessible(true);
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            mMessageView.setText("ceshi");
            mMessageView.setTextColor(Color.BLUE);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}