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

@WebServlet("/system/updateUser")
public class UserUpdateServlet extends HttpServlet{
	IUserService userService=new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 跳转修改页面
		// 1.接参
		User user=RequestBeanUtils.requestToBean(req, User.class);
		//2.根据用户ID查询对象
		user=userService.queryUser(user).get(0);
		//3.返回
		req.setAttribute("user", user);
		req.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接参 
		User user=RequestBeanUtils.requestToBean(req, User.class);
		//调用
		boolean flag=userService.updateUser(user);
		//3.跳转，返回
		if (flag) {
			req.setAttribute("tip", "修改成功");
		}else {
			req.setAttribute("tip", "修改失败");
		}
		req.setAttribute("user", user);
		req.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(req, resp);
	}
	
}
