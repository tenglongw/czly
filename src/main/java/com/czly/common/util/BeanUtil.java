package com.czly.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

public class BeanUtil {
	
	private BeanUtil() {
	}

	/**
	 * 拷贝bean属性。修改自org.springframework.beans.BeanUtils
	 * @param source
	 * @param target
	 * @param ignoreNull 是否忽略null属性，source中的值为null的属性将不会被拷贝
	 */
	public static void copyProperties(Object source, Object target,boolean ignoreNull)
	{
		Class targetClass = target.getClass();
		
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(targetClass);
		
		for (int i = 0; i < targetPds.length; i++)
		{
			PropertyDescriptor targetPd = targetPds[i];
			String propertyName=targetPd.getName();
			
			if (targetPd.getWriteMethod() != null
					&&!propertyName.endsWith("String")//过滤日期的String转换方法调用
					)
			{
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null)
				{
					try
					{
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source, new Object[0]);
						
						//空值将不会被拷贝
						if(value==null&&ignoreNull)
							continue;
						
						Method writeMethod = targetPd.getWriteMethod();
						if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
							writeMethod.setAccessible(true);
						}
						writeMethod.invoke(target, new Object[] {value});
					}
					catch(Exception ex)
					{
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}
		}
	}
}
