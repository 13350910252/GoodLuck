package com.example.goodluck.modeule.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.goodluck.R;
import com.example.goodluck.base.activity.PresenterBaseActivity;
import com.example.goodluck.databinding.ActivityLoginBinding;
import com.example.goodluck.modeule.login.mvp.LoginPresenter;
import com.example.goodluck.modeule.login.mvp.LoginView;
import com.example.goodluck.modeule.work.WorkActivity;
import com.example.goodluck.utils.Constant;
import com.example.goodluck.utils.StatusBarUtil;
import com.example.goodluck.utils.save.SharedPrefsMgr;
import com.google.gson.JsonObject;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends PresenterBaseActivity<ActivityLoginBinding, LoginPresenter> implements LoginView {
    private Tencent mTencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (UserInfo.getUserInfo(this).getObjectId() != null) {
            goWork();
            return;
        }
        initLogin();
        initViews();
        bindData();
    }

    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(mContext));
        binding.headerLayout.headerCenterTitle.setText(R.string.login_title);
        binding.headerLayout.headerBackImg.setVisibility(View.GONE);

        binding.ivQq.setOnClickListener(this);
        binding.ivWeixin.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (isClick) {
            return;
        }
        isClick = true;
        new Handler().postDelayed(runnable, 500);
        if (view == binding.ivQq) {
            if (!mTencent.isSessionValid()) {
                mTencent.login(LoginActivity.this, "all", iUiListener);
            }
        } else if (view == binding.ivWeixin) {
        } else if (view == binding.btnLogin) {
            if (UserInfo.getUserInfo(this).getObjectId() != null) {
                goWork();
                return;
            }
            BmobQuery<UserInfo> bmobQuery = new BmobQuery<>();
            bmobQuery.addWhereEqualTo("userName", "123");
            bmobQuery.addWhereEqualTo("passWord", "123");
            bmobQuery.findObjects(new FindListener<UserInfo>() {
                @Override
                public void done(List<UserInfo> list, BmobException e) {
                    if (e == null) {
                        if (list.size() == 0) {
                            Toast.makeText(mContext, "请检查你的账号和密码", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                            UserInfo.saveUserInfo(list.get(0), mContext);
                            goWork();
                        }
                    }
                }
            });
        }
    }

    private void initLogin() {
        if (mTencent == null)
            mTencent = Tencent.createInstance(Constant.APP_ID, this.getApplicationContext());
    }

    private IUiListener iUiListener = new BaseUiListener() {
        @Override
        protected void doComplete(JsonObject values) {
            super.doComplete(values);
            Log.d(Constant.TAG, "doComplete: " + values);
            try {
                String token = values.get("access_token").getAsString();
                String expires = values.get("expires_in").getAsString();
                String openId = values.get("openid").getAsString();
                //三个月，可以表示过期时间
                long expiresTime = values.get("expires_time").getAsLong();
                SharedPrefsMgr.setQqToken(token);
                SharedPrefsMgr.setQqOpenId(openId);
                SharedPrefsMgr.setQqExpires(expires);
            } catch (Exception e) {

            }

        }
    };

    private void goWork() {
        Intent intent = new Intent();
        intent.setClass(mContext, WorkActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, iUiListener);
            goWork();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void accountLoginSuccess() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }
}