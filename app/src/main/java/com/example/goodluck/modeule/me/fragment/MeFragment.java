package com.example.goodluck.modeule.me.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.goodluck.R;
import com.example.goodluck.base.fragment.AppBaseFragment;
import com.example.goodluck.databinding.FragmentMePageBinding;
import com.example.goodluck.modeule.login.activity.BaseUiListener;
import com.example.goodluck.modeule.work.WorkActivity;
import com.example.goodluck.utils.AppClass;
import com.example.goodluck.utils.Constant;
import com.example.goodluck.utils.GlideUtils;
import com.example.goodluck.utils.save.SharedPrefsMgr;
import com.example.goodluck.widget.TakePhotoDialogFragment;
import com.google.gson.JsonObject;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.Tencent;

public class MeFragment extends AppBaseFragment<FragmentMePageBinding> {
    private Tencent mTencent;
    private UserInfo userInfo;
    private QQToken qqToken;
    private String headerUrl;
    private String nickName;

    private TakePhotoDialogFragment takePhotoDialogFragment;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        bindData();
    }

    WorkActivity workActivity;

    @Override
    protected void initViews(View view) {
        workActivity = (WorkActivity) getActivity();
        workActivity.binding.headerLayout.headerEnd.setText(R.string.setting);
        workActivity.binding.headerLayout.headerEnd.setVisibility(View.VISIBLE);
        workActivity.binding.headerLayout.headerEnd.setOnClickListener(this);
        binding.ivHeaderPicture.setOnClickListener(this);
    }

    @Override
    protected void bindData() {
        super.bindData();
    }

    @Override
    public void onResume() {
        super.onResume();
        setHeaderPicture();

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = null;
        if (v == binding.ivHeaderPicture) {
            takePhotoDialogFragment = new TakePhotoDialogFragment();
            takePhotoDialogFragment.show(getChildFragmentManager(), "takePhoto");
        } else if (workActivity != null && workActivity.binding.headerLayout.headerEnd == v) {
            intent = new Intent();
            intent.setComponent(new ComponentName(getContext(), AppClass.settingActivity));
            startActivity(intent);
        }
    }

    private void setHeaderPicture() {
        String token = SharedPrefsMgr.getQqToken();
        String expires = SharedPrefsMgr.getQqExpires();
        String openId = SharedPrefsMgr.getQqOpenId();
        if (TextUtils.isEmpty(token) || TextUtils.isEmpty(expires) || TextUtils.isEmpty(openId)) {
            return;
        }
        if (mTencent == null) {
            mTencent = Tencent.createInstance(Constant.APP_ID, getActivity().getApplicationContext());
            mTencent.setOpenId(openId);
            mTencent.setAccessToken(token, expires);
            qqToken = mTencent.getQQToken();
            userInfo = new UserInfo(getContext(), qqToken);
            userInfo.getUserInfo(new BaseUiListener() {
                @Override
                protected void doComplete(JsonObject values) {
                    super.doComplete(values);
                    Log.d(Constant.TAG, "doComplete: " + values.toString());
                    nickName = values.get("nickname").getAsString();
                    headerUrl = values.get("figureurl_2").getAsString();
                    GlideUtils.loadUrl(getContext(), binding.ivHeaderPicture, ContextCompat.getDrawable(getContext(), R.drawable.picture), headerUrl, null
                            , null, true, false);
                }
            });
        }
    }

}
