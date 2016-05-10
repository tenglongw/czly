package com.czly.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czly.entity.User;

public class BaseController implements HandlerInterceptor {
	private static Logger logger = LogManager.getLogger(BaseController.class);
	public static User user = null;
	
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
		throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return true;
	}


	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView ) throws Exception {
		if ( isLegalView(modelAndView) ) {
			modelAndView.addObject("newdate", new Date());
		}
	}


	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex )
		throws Exception {
	}


	/**
	 * 判断是否为合法的视图地址
	 * <p>
	 * 
	 * @param modelAndView
	 *            spring 视图对象
	 * @return boolean
	 */
	protected boolean isLegalView( ModelAndView modelAndView ) {
		boolean legal = false;
		if ( modelAndView != null ) {
			String viewUrl = modelAndView.getViewName();
			if ( viewUrl != null && viewUrl.contains("redirect:") ) {
				legal = false;
			} else {
				legal = true;
			}
		}
		return legal;
	}
}
