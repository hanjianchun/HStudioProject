package com.hjc.myselfutils.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * 自定义栈来管理Activity
 * 
 * @author Han
 * 
 */
public class MyActivityManager {
	private static Stack<Activity> activityStack;
	private static MyActivityManager instance;

	private MyActivityManager() {
	}

	/**
	 * 
	* @Title: getActivityManager 
	* @Description: TODO 获取栈实例
	* @param @return    参数列表
	* @return MyActivityManager    返回类型
	* @throws
	 */
	public static MyActivityManager getActivityManager() {
		if (instance == null) {
			instance = new MyActivityManager();
		}
		return instance;
	}
	/**
	 * 
	* @Title: popActivity 
	* @Description: TODO 退出所有的栈
	* @param     参数列表
	* @return void    返回类型 
	* @throws
	 */
	public void popActivity() {
		if (activityStack != null && activityStack.size() > 0) {
			Activity activity = activityStack.lastElement();
			if (activity != null) {
				activity.finish();
				activityStack.remove(activity);
				activity = null;
			}
		}
	}

	/**
	 * 
	* @Title: popActivity 
	* @Description: TODO 退出一个Activity
	* @param @param activity    参数列表
	* @return void    返回类型 
	* @throws
	 */
	public void popActivity(Activity activity) {
		if (activityStack != null && activityStack.size() > 0) {
			if (activity != null) {
				activity.finish();
				activityStack.remove(activity);
				activity = null;
			}
		}
	}

	/**
	* @Title: currentActivity 
	* @Description: TODO 获得当前栈顶Activity
	* @param @return    参数列表
	* @return Activity    返回类型 
	* @throws
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	* @Title: pushActivity 
	* @Description: TODO 将当前Activity推入栈中
	* @param @param activity    参数列表
	* @return void    返回类型 
	* @throws
	 */
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	/**
	* @Title: popAllActivity 
	* @Description: TODO 退出栈中所有的Activity 
	* @param     参数列表
	* @return void    返回类型 
	* @throws
	 */
	public void popAllActivity() {
		if (activityStack != null) {
			while (activityStack.size() > 0) {
				Activity activity = currentActivity();
				if (activity == null)
					break;
				popActivity(activity);
			}
		}
	}

	/**
	 * 
	* @Title: popAllActivityExceptOne 
	* @Description: TODO 退出栈中所有Activity 除了class
	* @param @param cls    参数列表
	* @return void    返回类型 
	* @throws
	 */
	public void popAllActivityExceptOne(Class cls) {
		if (activityStack != null) {
			while (activityStack.size() > 0) {
				Activity activity = currentActivity();
				if (activity == null) {
					break;
				}
				if (activity.getClass().equals(cls)) {
					break;
				}
				popActivity(activity);
			}
		}
	}

}
