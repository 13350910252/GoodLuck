package com.example.goodluck.modeule.tools.task.mvp;

import android.content.Context;
import android.util.Log;

import com.example.goodluck.database.DaoMaster;
import com.example.goodluck.database.DaoSession;
import com.example.goodluck.database.TaskEntityDao;
import com.example.goodluck.modeule.tools.task.entity.TaskEntity;
import com.example.goodluck.mvp.BaseModel;
import com.example.goodluck.utils.CallBack;

import java.util.List;

public class TaskModel extends BaseModel {
    DaoMaster.DevOpenHelper helper;
    DaoMaster daoMaster;
    DaoSession daoSession;
    TaskEntityDao taskEntityDao;

    Context context;

    public TaskModel(Context context) {
        this.context = context;
        initDataBase();
    }

    private void initDataBase() {
        helper = new DaoMaster.DevOpenHelper(context, "goodluck.db", null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        taskEntityDao = daoSession.getTaskEntityDao();
    }

    public void addSQLiteData(String title, String content, CallBack.SQLiteCallBack sqLiteCallBack) {
        try {
            TaskEntity entity = new TaskEntity();
            entity.setTitle(title);
            entity.setContent(content);
            taskEntityDao.insert(entity);
            sqLiteCallBack.onSqlSuccess("添加任务成功");
        } catch (Exception e) {
            Log.d("abcd", "addSQLiteData: " + e.toString());
            sqLiteCallBack.onSqlFail("添加任务失败");
        }
    }

    public List querySQLiteDataForSize(Class clazz, int pageNo, CallBack.SQLiteCallBack sqLiteCallBack) {
        try {
            List list = daoSession.queryBuilder(clazz).offset(pageNo * 10).limit(10).list();
            sqLiteCallBack.onSqlSuccess("添加任务失败");
            return list;
        } catch (Exception e) {
            sqLiteCallBack.onSqlFail("添加任务失败");
            return null;
        }
    }

    /**
     * 清除数据库
     */
    public void clearAllData() {
        taskEntityDao.deleteAll();
    }
}
