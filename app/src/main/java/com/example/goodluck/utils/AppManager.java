package com.example.goodluck.utils;

import java.util.Map;
import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * @title AppManager
 * @description Activity管理类，用于Activity的管理和应用程序退出
 * @author zuolong
 * @date 2013-6-26
 * @version V1.0
 */
public class AppManager {

	private static Stack<Activity> activityStack;
	private static AppManager instance;

	private AppManager() {
	}

	public static AppManager getAppManager() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		if (activityStack != null) {
			for (int i = 0, size = activityStack.size(); i < size; i++) {
				if (null != activityStack.get(i)) {
					activityStack.get(i).finish();
				}
			}
			activityStack.clear();
		}
	}

	/**
	 * @description 结束所有Activity除了指定的Activity
	 * @param exceptActivityMap
	 *            指定的不被销毁的Activity集合
	 * @date 2013-11-20
	 * @author zuolong
	 */
	public void finishAllActivityExcept(Map<String, Activity> exceptActivityMap) {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			// 指定的Activity不被销毁
			if (null != activityStack.get(i)
					&& !exceptActivityMap.containsValue(activityStack.get(i))) {
				activityStack.get(i).finish();
			}
		}
	}

	/**
	 * 判断指定Activity是否存在堆栈中
	 * 
	 * @description
	 * @param activityName
	 * @return
	 * @date 2014-5-13
	 * @author zuolong
	 */
	public boolean hasActivity(String activityName) {
		if (activityStack != null) {
			for (int i = 0, size = activityStack.size(); i < size; i++) {
				if (null != activityStack.get(i)
						&& activityName.equals(activityStack.get(i).getClass()
								.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 退出应用程序
	 * 
	 * @description
	 * @param context
	 * @date 2014-6-13
	 * @author zuolong
	 */
	public void appExit(Context context) {
		appExit(context, true);
	}

	/**
	 * 退出应用程序
	 * 
	 * @description
	 * @param context
	 * @param exit
	 *            是否调用System.exit(0);
	 * @date 2014-6-13
	 * @author zuolong
	 */
	public void appExit(Context context, boolean exit) {
		try {
			finishAllActivity();
			// ActivityManager activityMgr = (ActivityManager) context
			// .getSystemService(Context.ACTIVITY_SERVICE);
			// activityMgr.restartPackage(context.getPackageName());
			if (exit) {
				System.exit(0);
			}
		} catch (Exception e) {
		}
	}
}