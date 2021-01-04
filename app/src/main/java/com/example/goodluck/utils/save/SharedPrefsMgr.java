package com.example.goodluck.utils.save;

import android.text.TextUtils;
import android.util.Base64;

import com.example.goodluck.base.App;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SharedPrefsMgr {
    /**
     * 版本号
     */
    public static final String BUILD_VERSION = "build_version";
    /**
     * qqToken
     */
    public static final String QQ_TOKEN = "qq_token";
    /**
     * openid
     */
    public static final String QQ_OPEN_ID = "qq_open_id";
    /**
     * 有效时间
     */
    public static final String QQ_EXPIRES = "qq_expires";
    /**
     * 用户头像
     */
    public static final String HEADER_PICTURE = "header_picture";
    /**
     * 用户昵称
     */
    public static final String NICK_NAME = "nick_name";
    /**
     * 用户账号
     */
    public static final String USER_NAME = "user_name";
    /**
     * 用户信息
     */
    public static final String USER_INFO = "user_info";
    /**
     * 获取服务地址
     *
     * @return
     */
    public static String getUserName() {
        return SharedPrefsUtil.getValue(App.context, USER_NAME, "");
    }

    /**
     * 存储服务地址
     *
     * @return
     */
    public static void setUserName(String platformUrl) {
        SharedPrefsUtil.putValue(App.context, USER_NAME, platformUrl);
    }

    /**
     * 获取用户的头像url
     *
     * @return
     */
    public static String getHeaderPicture() {
        return SharedPrefsUtil.getValue(App.context, HEADER_PICTURE, "");
    }

    /**
     * 存储用户的头像url
     *
     * @return
     */
    public static void setHeaderPicture(String platformUrl) {
        SharedPrefsUtil.putValue(App.context, HEADER_PICTURE, platformUrl);
    }

    /**
     * 获取用户的昵称
     *
     * @return
     */
    public static String getNickName() {
        return SharedPrefsUtil.getValue(App.context, NICK_NAME, "");
    }

    /**
     * 存储用户的昵称
     *
     * @return
     */
    public static void setNickName(String platformUrl) {
        SharedPrefsUtil.putValue(App.context, NICK_NAME, platformUrl);
    }

    /**
     * 获取QQToken
     *
     * @return
     */
    public static String getQqToken() {
        return SharedPrefsUtil.getValue(App.context, QQ_TOKEN, "");
    }

    /**
     * 存储QQToken
     *
     * @return
     */
    public static void setQqToken(String platformUrl) {
        SharedPrefsUtil.putValue(App.context, QQ_TOKEN, platformUrl);
    }

    /**
     * 获取QQ信息
     *
     * @return
     */
    public static String getQqOpenId() {
        return SharedPrefsUtil.getValue(App.context, QQ_OPEN_ID, "");
    }

    /**
     * 存储QQ信息
     *
     * @return
     */
    public static void setQqOpenId(String platformUrl) {
        SharedPrefsUtil.putValue(App.context, QQ_OPEN_ID, platformUrl);
    }

    /**
     * 获取QQ信息
     *
     * @return
     */
    public static String getQqExpires() {
        return SharedPrefsUtil.getValue(App.context, QQ_EXPIRES, " ");
    }

    /**
     * 存储QQ信息
     *
     * @return
     */
    public static void setQqExpires(String platformUrl) {
        SharedPrefsUtil.putValue(App.context, QQ_EXPIRES, platformUrl);
    }

    /********************************************* 系统数据配置 ************************************************/
    public static void remove(String key) {
        SharedPrefsUtil.remove(App.context, key);
    }

    private static String object2string(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            byte[] bytes = Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                    objectOutputStream = null;
                    byteArrayOutputStream = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Object string2object(String str) {
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                byte[] bytes = Base64.decode(str.getBytes(), Base64.DEFAULT);
                byteArrayInputStream = new ByteArrayInputStream(bytes);
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                return objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (objectInputStream != null) {
                        objectInputStream.close();
                        objectInputStream = null;
                        byteArrayInputStream = null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return null;
        }

    }

}
