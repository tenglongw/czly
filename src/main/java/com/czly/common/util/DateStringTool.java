package com.czly.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.czly.common.util.lang.Strings;

public class DateStringTool {
	
	public static final int MillisecondsOfDay = 86400000;
	
	/**
	 * DateToStr 'yyyyMMdd'
	 * @param date
	 * @return
	 */
	public static String dateToStrM1(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		"yyyyMMdd");
		return simpleDateFormat.format(date);
	}
	
	/**
	 * DateToStr 'yyyy-MM-dd HH:mm:ss'
	 * @param date
	 * @return
	 */
	public static String dateToStrM2(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}
	
	/**
	 * DateToStr 'yyyy-MM-dd'
	 * @param DateToStr
	 * @return
	 */
	public static String dateToStrM3(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		"yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}
	
	/**
	 * DateToStr 'yyyyMMddHHmmss'
	 * @param date
	 * @return
	 */
	public static String dateToStrM4(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		"yyyyMMddHHmmss");
		return simpleDateFormat.format(date);
	}
	
	/**
	 * DateToStr 'MM-dd HH:mm:ss'
	 * @param date
	 * @return
	 */
	public static String dateToStrM5(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		"MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}
	
	/**
	 * DateToStr 'HH:mm:ss'
	 * @param date
	 * @return
	 */
	public static String dateToStrM6(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		"HH:mm:ss");
		return simpleDateFormat.format(date);
	}
	
	/**
	 * StrToDate 'yyyy-MM-dd HH:mm:ss'
	 * @param StrToDate 'yyyy-MM-dd HH:mm:ss'
	 * @return Date
	 */
	public static Date strToDateM1(String StrToDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return simpleDateFormat.parse(StrToDate);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * StrToDate 'yyyy-MM-dd'
	 * @param StrToDate 'yyyy-MM-dd'
	 * @return
	 */
	public static Date strToDateM2(String StrToDate)
	{
		if (Strings.isEmpty(StrToDate)) return null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return simpleDateFormat.parse(StrToDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * StrToDate 'yyyyMMdd'
	 * @param StrToDate 'yyyyMMdd'
	 * @return
	 */
	public static Date strToDateM3(String StrToDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			return simpleDateFormat.parse(StrToDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * StrToDate 'yyyy-MM-dd HH:mm'
	 * @param StrToDate 'yyyy-MM-dd HH:mm'
	 * @return
	 */
	public static Date strToDateM4(String StrToDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return simpleDateFormat.parse(StrToDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * StrToDate 'yyyy/MM/dd'
	 * @param StrToDate 'yyyy/MM/dd'
	 * @return
	 */
	public static Date strToDateM5(String StrToDate)
	{
		if (Strings.isEmpty(StrToDate)) return null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			return simpleDateFormat.parse(StrToDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * StrToDate 'yyyy-MM-dd HH:mm:ss'
	 * @param StrToDate 'yyyy-MM-dd HH:mm:ss'
	 * @return Date
	 */
	public static Date strToDateM6(String StrToDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			return simpleDateFormat.parse(StrToDate);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		return null;
	}

	public static Date nextDay(Date strToDateM2) {
		if (strToDateM2 == null) return null;
		return new Date(strToDateM2.getTime()+24*3600*1000);
	}
}
