package com.example.goodluck.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class TestBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("yinp", "onReceive: ");
        Toast.makeText(context,"测试",Toast.LENGTH_SHORT).show();
    }
}
