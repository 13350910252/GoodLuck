package com.example.goodluck.utils;

import android.content.Context;

import com.example.goodluck.database.DaoMaster;
import com.example.goodluck.database.DaoSession;
import com.example.goodluck.database.TaskEntityDao;
import com.example.goodluck.modeule.tools.task.entity.TaskEntity;

import java.util.List;

public class GreenDaoUtils {
    static DaoMaster.DevOpenHelper helper;
    static DaoMaster daoMaster;
    static DaoSession daoSession;
    static TaskEntityDao taskEntityDao;

    static Context mContext;

    public static GreenDaoUtils getInstance(Context context) {
        mContext = context;
        initDataBase();
        return new GreenDaoUtils();
    }

    private static void initDataBase() {
        helper = new DaoMaster.DevOpenHelper(mContext, "goodluck.db", null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        taskEntityDao = daoSession.getTaskEntityDao();
    }

    /**
     * 通过页数查询，默认每页10条
     * 查询时从第0页开始的
     *
     * @param clazz
     * @param pageNo
     * @return
     */
    public List querySQLiteForSize(Class clazz, int pageNo) {
        List list = daoSession.queryBuilder(TaskEntity.class).offset(pageNo * Constant.PAGE_NUM).limit(Constant.PAGE_NUM).list();
        return list;
    }

}
