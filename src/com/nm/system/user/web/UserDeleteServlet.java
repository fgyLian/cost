package com.nm.system.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.entity.User;
import com.nm.system.user.service.IUserService;
import com.nm.system.user.service.impl.UserServiceImpl;

@WebServlet("/system/deleteUser")
public class UserDeleteServlet extends HttpServlet{
	IUserService userService=new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user=RequestBeanUtils.requestToBean(req, User.class);
		boolean flag=userService.deleteUser(user);
		
		//删除后刷新页面
		req.getRequestDispatcher("/system/userQuery").forward(req, resp);
	}
	
}
