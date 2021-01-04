package com.example.goodluck.base.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.example.goodluck.R;
import com.example.goodluck.utils.FitScreenUtil;
import com.example.goodluck.utils.StatusBarUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AppBaseActivity<T extends ViewBinding> extends BaseActivity {
    protected T binding;
    //记录按下,防止连续点击
    public boolean isClick = false;
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            isClick = false;
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingLayout();
        FitScreenUtil.setCustomDensity(mActivity, getApplication());
        StatusBarUtil.setTranslucentStatus(mActivity);
    }

    public void bindingLayout() {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            try {
                Class<T> clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];

                Method method = clazz.getMethod("inflate", LayoutInflater.class);
                binding = (T) method.invoke(null, getLayoutInflater());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            setContentView(binding.getRoot());
        }
    }

    protected abstract void initViews();

    protected void bindData() {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.header_back_img) {
            finish();
        }
    }

    /**
     * 设置占位View的高度，主要是用于浸入式状态栏
     *
     * @param height 状态栏高度
     */
    protected void setStatusBarHeight(int height) {
        View view = findViewById(R.id.view_status);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
    }
}
