package com.czly.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.czly.entity.User;

@Component
public class CheckLoginFilter implements Filter {
	private static final String LOGIN_URL = "login";
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(true);
		// 从session 里面获取用户名的信息
		User crUser = (User) session.getAttribute("user");
		// 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
		System.out.println(request.getRequestURI());
		if (crUser == null || "".equals(crUser.toString())) {
			response.sendRedirect(LOGIN_URL);
		}else{
			chain.doFilter(req, res);
		}
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}