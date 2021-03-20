package com.example.goodluck.modeule.home.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.goodluck.R;
import com.example.goodluck.base.activity.AppBaseActivity;
import com.example.goodluck.databinding.ActivityWanAndroidWebViewBinding;

public class WanAndroidWebViewActivity extends AppBaseActivity<ActivityWanAndroidWebViewBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        bindData();
    }

    @Override
    protected void initViews() {
    }
}