package com.example.goodluck.web.xutils;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;


import com.example.goodluck.R;
import com.example.goodluck.utils.AppManager;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

public abstract class XUtilsRequestCallback implements Callback.CommonCallback<XUtilsBaseResp> {
    private Context context;
    private String respId;

    public XUtilsRequestCallback(Context context) {
        this(context, null);
    }

    public XUtilsRequestCallback(Context context, String respId) {
        this.context = context;
        this.respId = respId;
    }

    protected void onDone(XUtilsBaseResp result, String respId) {
    }

    protected void onFail(String respId) {
    }

    @Override
    public final void onSuccess(XUtilsBaseResp resp) {
        LogUtil.w("code:" + resp.code);
        LogUtil.w("msg:" + resp.msg);
        LogUtil.w("data:" + resp.data);
        if (XUtilsRequestCode.SUCCESS.equals(resp.code)) {
            onDone(resp, this.respId);
        } else if (XUtilsRequestCode.NO_NETWORK.equals(resp.code)) {
//            ToastWidget.makeText(context, resp.msg).show();
            LogUtil.w("========" + resp.msg + "========");
            onFail(this.respId);
        } else if (XUtilsRequestCode.SERVER_ERR.equals(resp.code)) {
//            ToastWidget.makeText(context, resp.msg).show();
            onFail(this.respId);
        } else if (XUtilsRequestCode.SHIP_REPEAT.equals(resp.code)) {
//            ToastWidget.makeText(context, resp.msg).show();
            onFail(this.respId);
        }
        LogUtil.w("========request end========");
    }

    @Override
    public final void onError(Throwable ex, boolean isOnCallback) {
//        if (ex instanceof ConnectException) {
////            ToastWidget.makeText(context, R.string.request_connect_error_msg).show();
//        } else if (ex instanceof SocketTimeoutException) {
////            ToastWidget.makeText(context, R.string.request_timeout_msg).show();
//        } else if (ex instanceof IOException) {
//            if (!TextUtils.isEmpty(ex.toString())
//                    && ex.toString().contains(XUtilsRequestCode.TOKEN_ERR)
//                    && ex.toString().contains("invalid_token")) {// token失效
//                // 关闭所有页面
//                AppManager.getAppManager().finishAllActivity();
//                Intent intent = new Intent(context, LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//                ToastWidget.makeText(context, R.string.login_invalid).show();
//                return;
//            } else {
//                ToastWidget.makeText(context, R.string.request_server_busy_msg).show();
//            }
//        }
//        onFail(this.respId);
//        LogUtil.w("========request onError========");
    }

    @Override
    public final void onCancelled(CancelledException cex) {
        LogUtil.w("========request onCancelled========");
    }

    @Override
    public final void onFinished() {
        LogUtil.w("========request end========");
    }

}
