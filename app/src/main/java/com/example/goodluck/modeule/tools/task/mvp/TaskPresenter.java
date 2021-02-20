package com.example.goodluck.modeule.tools.task.mvp;

import android.text.TextUtils;

import com.example.goodluck.base.activity.AppBaseActivity;
import com.example.goodluck.base.activity.AppBaseFragmentActivity;
import com.example.goodluck.modeule.tools.task.activity.AddTaskActivity;
import com.example.goodluck.modeule.tools.task.activity.DayTaskActivity;
import com.example.goodluck.utils.CallBack;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class TaskPresenter {
    private WeakReference<AppBaseFragmentActivity> wfFragmentActivity;
    private WeakReference<AppBaseActivity> wfActivity;
    private TaskModel taskModel;

    public TaskPresenter(AppBaseFragmentActivity activity) {
        this.wfFragmentActivity = new WeakReference<>(activity);
        this.taskModel = new TaskModel(activity);
    }

    public TaskPresenter(AppBaseActivity activity) {
        this.wfActivity = new WeakReference<>(activity);
        this.taskModel = new TaskModel(activity);
    }

    public void addSQLiteData(String title, String content) {
        final AddTaskActivity activity = (AddTaskActivity) wfFragmentActivity.get();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
            activity.onSqlFail("标题或内容不能为空");
        } else {
            if (taskModel == null) {
                return;
            }
            taskModel.addSQLiteData(title, content, new CallBack.SQLiteCallBack() {
                @Override
                public void onSqlSuccess(String result) {
                    activity.onSqlSuccess(result);
                }

                @Override
                public void onSqlFail(String result) {
                    activity.onSqlFail(result);
                }
            });
        }
    }

    public void querySQLiteData(Class clazz, int pageNo) {
        final DayTaskActivity activity = (DayTaskActivity) wfActivity.get();
        final List list = taskModel.querySQLiteDataForSize(clazz, pageNo, new CallBack.SQLiteCallBack() {

            @Override
            public void onSqlSuccess(String result) {
                List list1 = new ArrayList();
            }

            @Override
            public void onSqlFail(String result) {

            }
        });
    }

    public void clearAllData() {
        taskModel.clearAllData();
    }
}
