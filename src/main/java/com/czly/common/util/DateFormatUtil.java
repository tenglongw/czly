package com.czly.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
	
	/**
	 * @param dateStr
	 * @return
	 */
	public static Timestamp stringToTimestamp(Object dateStr) {
		if(dateStr == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(dateStr.toString());
			date.getTime();
			cal.setTime(date);
			return new Timestamp(cal.getTimeInMillis());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Timestamp stringToTimestamp(Object dateStr,String format) {
		if(dateStr == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(dateStr.toString());
			date.getTime();
			cal.setTime(date);
			return new Timestamp(cal.getTimeInMillis());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String getTime(String arg_begin,String arg_end){
		
		String time = "";
		
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long between = 0;
        try {
        	//当前时间
            java.util.Date begin = dfs.parse(arg_begin);
            //记录时间
            java.util.Date end = dfs.parse(arg_end);
            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        //System.out.println(day + "天" + hour + "小时" + min + "分" + s + "秒" + ms+ "毫秒");
        if(day > 0){
        	//多少天
        	time = day + "天前";
        	if(day > 2){
        		SimpleDateFormat dfs_temp = new SimpleDateFormat("MM-dd HH:mm");
        		java.util.Date data;
				try {
					data = dfs_temp.parse(arg_begin);
					time = dfs_temp.format(data);
				} catch (ParseException e) {
					e.printStackTrace();
				}
        	}
        }else if(hour > 0){
        	//多少小时
        	time = hour + "小时前";
        }else if(min > 0){
        	//多少分钟
        	time = min + "分钟前";
        }else {
        	time = s + "秒前";
        }
        //System.out.println("--"+time);
        return time;
	}
	
	
	public static String formatString(Timestamp da){
		String datastring = null;
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
		datastring = sdf.format(da);
		return datastring;
	}
	
	public static String formatString(Timestamp da,String format){
		String datastring = null;
		SimpleDateFormat   sdf   =   new   SimpleDateFormat(format); 
		datastring = sdf.format(da);
		return datastring;
	}
	
	/**
	 * 
	 * @param date  yyyy-MM-dd
	 * @return
	 */
	public static Date formatDate(Timestamp time,String format){
		String formatString = formatString(time,format);
		Timestamp stringToTimestamp = stringToTimestamp(formatString,format);
		return new Date(stringToTimestamp.getTime());
	}
		
}
