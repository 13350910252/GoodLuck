package com.example.goodluck.modeule.login.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.Gson;

import cn.bmob.v3.BmobObject;

public class UserInfo extends BmobObject implements Parcelable {
    //正常登录的信息
    private String userName;
    private String passWord;
    //qq的信息
    private String qqToken;
    private String qqExpires;
    private String qqOpenId;

    public UserInfo() {
    }

    public UserInfo(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(passWord);
        dest.writeString(qqToken);
        dest.writeString(qqExpires);
        dest.writeString(qqOpenId);
    }

    private UserInfo(Parcel in) {
        userName = in.readString();
        passWord = in.readString();
        qqToken = in.readString();
        qqExpires = in.readString();
        qqOpenId = in.readString();
    }

    //    SharedPreference 相关修改使用 apply 方法进行提交会先写入内存，然后异步写入磁盘，commit
//    方法是直接写入磁盘。如果频繁操作的话 apply 的性能会优于 commit，apply会将最后修改内容写入磁盘。
//    但是如果希望立刻获取存储操作的结果，并据此做相应的其他操作，应当使用 commit。
    public static UserInfo getUserInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserInfo", 0);
        String data = sharedPreferences.getString("UserInfo", "{}");
        return fromJson(data);
    }

    /**
     * 保存用户信息
     */
    public static void saveUserInfo(String data, Context context) {
        if (!TextUtils.isEmpty(data)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("UserInfo", data);
            editor.apply();
        }
    }

    /**
     * 保存用户信息
     */
    public static void saveUserInfo(UserInfo userInfo, Context context) {
        if (userInfo != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("UserInfo", toJson(userInfo));
            editor.apply();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public static String toJson(UserInfo userInfo) {
        return new Gson().toJson(userInfo);
    }

    public static UserInfo fromJson(String json) {
        return new Gson().fromJson(json, UserInfo.class);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getQqToken() {
        return qqToken;
    }

    public void setQqToken(String qqToken) {
        this.qqToken = qqToken;
    }

    public String getQqExpires() {
        return qqExpires;
    }

    public void setQqExpires(String qqExpires) {
        this.qqExpires = qqExpires;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }
}
