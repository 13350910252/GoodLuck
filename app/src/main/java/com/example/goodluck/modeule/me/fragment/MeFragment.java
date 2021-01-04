package com.example.goodluck.modeule.me.fragment;

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
import com.example.goodluck.utils.Constant;
import com.example.goodluck.utils.GlideUtils;
import com.example.goodluck.utils.save.SharedPrefsMgr;
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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        bindData();
    }
    @Override
    protected void initViews(View view) {

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
