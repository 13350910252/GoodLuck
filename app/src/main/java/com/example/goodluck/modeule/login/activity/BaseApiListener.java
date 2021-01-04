package com.example.goodluck.modeule.login.activity;

import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

public class BaseApiListener implements IRequestListener {
//    @Override
//    public void onComplete(final JSONObject response, Object state) {
////        showResult("IRequestListener.onComplete:", response.toString());
//        doComplete(response, state);
//    }
//    protected void doComplete(JSONObject response, Object state) {
//    }
//    @Override
//    public void onIOException(final IOException e, Object state) {
////        showResult("IRequestListener.onIOException:", e.getMessage());
//    }
//    @Override
//    public void onMalformedURLException(final MalformedURLException e,
//                                        Object state) {
////        showResult("IRequestListener.onMalformedURLException", e.toString());
//    }
//    @Override
//    public void onJSONException(final JSONException e, Object state) {
////        showResult("IRequestListener.onJSONException:", e.getMessage());
//    }
//    @Override
//    public void onConnectTimeoutException(ConnectTimeoutException arg0,
//                                          Object arg1) {
//// TODO Auto-generated method stub
//    }
//    @Override
//    public void onSocketTimeoutException(SocketTimeoutException arg0,
//                                         Object arg1) {
//// TODO Auto-generated method stub
//    }
//    //1.4版本中IRequestListener 新增两个异常
//    @Override
//    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException e, Object state){
//// 当前网络不可用时触发此异常
//    }
//    @Override
//    public void onHttpStatusException(HttpUtils.HttpStatusException e, Object state) {
//// http请求返回码非200时触发此异常
//    }
//    public void onUnknowException(Exception e, Object state) {
//// 出现未知错误时会触发此异常
//    }

    @Override
    public void onComplete(JSONObject jsonObject) {

    }

    @Override
    public void onIOException(IOException e) {

    }

    @Override
    public void onMalformedURLException(MalformedURLException e) {

    }

    @Override
    public void onJSONException(JSONException e) {

    }

    @Override
    public void onConnectTimeoutException(ConnectTimeoutException e) {

    }

    @Override
    public void onSocketTimeoutException(SocketTimeoutException e) {

    }

    @Override
    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException e) {

    }

    @Override
    public void onHttpStatusException(HttpUtils.HttpStatusException e) {

    }

    @Override
    public void onUnknowException(Exception e) {

    }
}