package com.nm.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nm.entity.User;
@WebFilter("/")
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// 判断用户是否登录或session是否过期
		HttpServletRequest req=(HttpServletRequest)arg0;
		HttpSession session=req.getSession();
		User user=(User) session.getAttribute("userInfo");
		//获取用户的访问地址
		String url=req.getRequestURI();
		if (user!=null) {//用户已经登录且session没有过期
			arg2.doFilter(arg0, arg1);
		}else if(url.contains("/login.jsp")) {//如果访问的是登录页面，不过滤，可以继续访问
			arg2.doFilter(arg0, arg1);
		}else if(url.contains("/doLogin")) {//如果用户正在执行登录操作，也可以继续访问
			arg2.doFilter(arg0, arg1);
		}else if(url.contains("/resource")) {//静态资源不过滤，也可以继续访问
			arg2.doFilter(arg0, arg1);
		}
		else{//
			//跳转中间页面
			req.getRequestDispatcher("/view/login_info.jsp").forward(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
