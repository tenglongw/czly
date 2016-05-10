package com.czly.common.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czly.entity.User;

/**
 * 拦截未登录用户
 */
public class SessionInterceptor implements HandlerInterceptor {
	private static final String LOGIN_URL = "/login";
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
		throws Exception {
		
		HttpSession session = request.getSession(true);
		// 从session 里面获取用户名的信息
		User crUser = (User) session.getAttribute("user");
		// 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
		System.out.println(request.getRequestURI());
		if (crUser == null || "".equals(crUser.toString())) {
			response.sendRedirect(LOGIN_URL);
			return false;
		}
		return true;
	}


	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView ) throws Exception {
	}

	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex )
		throws Exception {
	}
}
