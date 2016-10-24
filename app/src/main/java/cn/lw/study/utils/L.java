package cn.lw.study.utils;

import android.util.Log;

import cn.lw.study.constants.ConstantsValue;

/**
 * Log统一管理类
 * 
 * @author specter
 * 
 */
public class L {

	private L() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	// 下面四个是默认tag的函数
	public static void i(String msg) {
		if (ConstantsValue.IS_DEBUG)
			Log.i(ConstantsValue.DEBUG_TAG, msg);
	}

	public static void d(String msg) {
		if (ConstantsValue.IS_DEBUG)
			Log.d(ConstantsValue.DEBUG_TAG, msg);
	}

	public static void e(String msg) {
		if (ConstantsValue.IS_DEBUG)
			Log.e(ConstantsValue.DEBUG_TAG, msg);
	}

	public static void v(String msg) {
		if (ConstantsValue.IS_DEBUG)
			Log.v(ConstantsValue.DEBUG_TAG, msg);
	}

	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg) {
		if (ConstantsValue.IS_DEBUG)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (ConstantsValue.IS_DEBUG)
			Log.d(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (ConstantsValue.IS_DEBUG)
			Log.e(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (ConstantsValue.IS_DEBUG)
			Log.v(tag, msg);
	}
}