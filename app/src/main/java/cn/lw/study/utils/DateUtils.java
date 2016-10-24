package cn.lw.study.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间辅助类
 * 
 * @author specter
 * 
 */
public class DateUtils {
	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private DateUtils() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 取yyyy-MM-dd HH:mm:ss的yyyy-MM-dd.
	 * 
	 * @param str
	 * @return
	 */
	public static String getDate(String str) {
		str = str.split(" ")[0];
		return str;
	}

	/**
	 * 根据时间戳返回日期格式字符串.
	 * 
	 * @param timeStamp
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateStr(long timeStamp) {
		Date date = new Date(timeStamp);
		return format.format(date);
	}

	/**
	 * 返回当前时间.yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		return format.format(date);
	}

	/**
	 * 格式化时间
	 * 
	 * @return
	 */
	public static String getFormatDay(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 ");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		try {
			date1 = sdf1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String str = sdf.format(date1);
		return str;
	}

	/**
	 * 返回小时时间
	 * 
	 * @return
	 */
	public static String getFormatHours(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		Date date1 = null;
		try {
			date1 = sdf1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String str = sdf.format(date1);
		return str;
	}
}
