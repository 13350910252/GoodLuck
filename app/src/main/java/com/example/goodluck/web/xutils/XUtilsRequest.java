package com.example.goodluck.web.xutils;

import android.content.Context;
import android.text.TextUtils;

import com.example.goodluck.utils.NetworkUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Field;

public class XUtilsRequest {
    /**
     * 统一的请求
     */
    public static final XUtilsApiType ApiType = XUtilsApiType.ONLY;
    /**
     * 统一的请求方法名
     */
    public static final String ACTION_NAME = "execute";
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 动作名
     */
    private String mActionName;
    /**
     * 请求对象
     */
    private org.xutils.common.Callback.Cancelable request;
    /**
     * 请求回调
     */
    protected Callback.CommonCallback mXUtilsCallback;
    /**
     * 请求参数对象
     */
    protected XUtilsBaseReqParams mReqParams;
    /**
     * 请求类型
     */
    protected XUtilsRequestType mRequestType;

    public XUtilsRequest(Context context, XUtilsBaseReqParams params, Callback.CommonCallback xUtilsCallback) {
        this(context, params, ACTION_NAME, xUtilsCallback);
    }

    public XUtilsRequest(Context context, XUtilsBaseReqParams params,
                         String actionName, Callback.CommonCallback xUtilsCallback) {
        this(context, params, actionName, XUtilsRequestType.POST_JSON, xUtilsCallback);
    }

    public XUtilsRequest(Context context, XUtilsBaseReqParams params, XUtilsRequestType requestType, Callback.CommonCallback xUtilsCallback) {
        this(context, params, ACTION_NAME, requestType, xUtilsCallback);
    }

    public XUtilsRequest(Context context, XUtilsBaseReqParams params,
                         String actionName, XUtilsRequestType requestType, Callback.CommonCallback xUtilsCallback) {
        this.mContext = context;
        this.mActionName = actionName;
        this.mReqParams = params;
        this.mRequestType = requestType;
        this.mXUtilsCallback = xUtilsCallback;
        LogUtil.w("========request begin========");
    }

    public String getMethod() {
        return mReqParams.getMethod();
    }

    /**
     * 封装url
     *
     * @return
     */
    protected String getUrl() {
        // 封装url
//        String url = SharedPrefsMgr.getPlatformUrl();
        String url = "te";
        // 判断地址中是否有http头，没有则添加
        if (!url.endsWith("/")) {
            url = url + "/";
        }
        // ex:http://10.10.1.34:9080/bmxx/login
        url = url + this.mActionName;
        LogUtil.w("request url:" + url);
        return url;
    }

    /**
     * @return
     * @throws IllegalAccessException
     * @description 设置请求参数
     * @date 2013-12-10
     * @author zuolong
     */
    protected RequestParams getRequestParams() {
        RequestParams reqParams = new RequestParams();
        reqParams.setUri(getUrl());
        try {
            if (ApiType == XUtilsApiType.ONLY) {
                if (mRequestType == XUtilsRequestType.GET) {
                    LogUtil.e("no support only and get request.");
                } else if (mRequestType == XUtilsRequestType.POST) {
                    if (mReqParams.getMethod().equals("login")) {
                        reqParams.setHeader("dataSource", "androidApp");// 数据源
                    } else {
//                        reqParams.setHeader("Authorization", SharedPrefsMgr.getAuthorization());// Authorization
                        reqParams.setHeader("dataSource", "androidApp");// 数据源
                    }
                    reqParams.addBodyParameter("method", mReqParams.getMethod());
                    Gson gson = new Gson();
                    String bodyContent = gson.toJson(mReqParams);
//                    // 请求主题参数进行编码，避免b/s端接收后出现乱码
//                    basicParams.context = URLEncoder.encode(paramsObj.toString(),
//                            CHARSET);
                    reqParams.addBodyParameter("context", bodyContent);
                } else if (mRequestType == XUtilsRequestType.POST_JSON) {
                    JsonObject jsonBodyContent = new JsonObject();
                    if (mReqParams.getMethod().equals("login") || mReqParams.getMethod().equals("getCommunicationReturn")) {
                        reqParams.setHeader("dataSource", "androidApp");// 数据源
                    } else {
//                        reqParams.setHeader("Authorization", SharedPrefsMgr.getAuthorization());// Authorization
                        reqParams.setHeader("dataSource", "androidApp");// 数据源
                    }
                    jsonBodyContent.addProperty("method", mReqParams.getMethod());
                    Gson gson = new Gson();
                    String bodyContent = gson.toJson(mReqParams);
//                    // 请求主题参数进行编码，避免b/s端接收后出现乱码
//                    bodyContent = URLEncoder.encode(bodyContent,
//                            "UTF-8");
                    jsonBodyContent.addProperty("context", bodyContent);
                    LogUtil.w("params:" + jsonBodyContent.toString());
                    reqParams.setAsJsonContent(true);
                    reqParams.setBodyContent(jsonBodyContent.toString());
                }
            } else if (ApiType == XUtilsApiType.MULTI) {
                if (mRequestType == XUtilsRequestType.GET) {
                    Class<? extends XUtilsBaseReqParams> cls = mReqParams.getClass();
                    Field[] fields = cls.getDeclaredFields();
                    // 循环遍历请求参数对象中的值
                    if (fields != null && fields.length > 0) {
                        JSONObject json = null;
                        for (Field field : fields) {
                            // 判断变量是否存在指定的注解
                            if (field.isAnnotationPresent(InjectReqParam.class)) {
                                // 获得该成员的annotation
                                InjectReqParam reqParam = field
                                        .getAnnotation(InjectReqParam.class);
                                // 通过反射获得该参数的名称
                                String name = reqParam.name();
                                // 如果没有设置name则默认变量名为参数名称
                                if (TextUtils.isEmpty(name)) {
                                    name = field.getName();
                                }
                                // 获取参数值
                                field.setAccessible(true);
                                Object object = field.get(mReqParams);
                                String value = null;
                                if (object != null) {
                                    value = object.toString();
                                }
                                // 存储参数
                                LogUtil.w(name + ":" + value);
                                reqParams.addQueryStringParameter(name, value);
                            }
                        }
                    }
                } else if (mRequestType == XUtilsRequestType.POST) {
                    Class<? extends XUtilsBaseReqParams> cls = mReqParams.getClass();
                    Field[] fields = cls.getDeclaredFields();
                    // 循环遍历请求参数对象中的值
                    if (fields != null && fields.length > 0) {
                        JSONObject json = null;
                        for (Field field : fields) {
                            // 判断变量是否存在指定的注解
                            if (field.isAnnotationPresent(InjectReqParam.class)) {
                                // 获得该成员的annotation
                                InjectReqParam reqParam = field
                                        .getAnnotation(InjectReqParam.class);
                                // 通过反射获得该参数的名称
                                String name = reqParam.name();
                                // 如果没有设置name则默认变量名为参数名称
                                if (TextUtils.isEmpty(name)) {
                                    name = field.getName();
                                }
                                // 获取参数值
                                field.setAccessible(true);
                                Object object = field.get(mReqParams);
                                String value = null;
                                if (object != null) {
                                    value = object.toString();
                                }
                                // 存储参数
                                LogUtil.w(name + ":" + value);
                                reqParams.addBodyParameter(name, value);
                            }
                        }
                    }
                } else if (mRequestType == XUtilsRequestType.POST_JSON) {
                    Gson gson = new Gson();
                    String bodyContent = gson.toJson(mReqParams);
                    reqParams.setAsJsonContent(true);
                    reqParams.setBodyContent(bodyContent);
                    LogUtil.w("params:" + reqParams.getBodyContent().toString());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return reqParams;
    }

    /**
     * 发送请求
     */
    public void sendRequest() {
        // 判断网络是否连接
        if (NetworkUtil.isNetworkConnected(mContext)) {
            LogUtil.w("request action=" + mReqParams.getMethod());
            // 根据不同请求类型调用不同类型的请求
            if (mRequestType == XUtilsRequestType.GET) {
                request = x.http().get(getRequestParams(), mXUtilsCallback);
            } else if (mRequestType == XUtilsRequestType.POST || mRequestType == XUtilsRequestType.POST_JSON) {
                request = x.http().post(getRequestParams(), mXUtilsCallback);
            }
        } else {
            XUtilsBaseResp resp = new XUtilsBaseResp();
            resp.code = XUtilsRequestCode.NO_NETWORK;
//            resp.msg = mContext.getString(com.example.framework.R.string.network_conntect_fail_msg);
            mXUtilsCallback.onSuccess(resp);
        }
    }

    /**
     * 取消请求
     */
    public void cancelRequest() {
        if (request != null) {
            request.cancel();
        }
        LogUtil.w(mActionName + " canceled");
    }
}
