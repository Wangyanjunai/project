package com.imooc.project.filter;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

@SuppressWarnings("unchecked")
public class MyInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取url
		String requestURI = request.getRequestURI();
		// /account/toList account/list
		String subString = requestURI.substring(1);
		// account/toList account
		int index = subString.indexOf("/");
		if (index != -1) {
			// account
			subString = subString.substring(0, index);
		}
		HashSet<String> urls = (HashSet<String>) request.getSession().getAttribute("module");
		// account是否在 该用户所有拥有权限的资源中
		boolean result = urls.stream().anyMatch(subString::equals);
		if (!result) {
			response.sendRedirect("/");
		}
		return result;
	}

}
