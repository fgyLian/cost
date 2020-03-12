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
@WebServlet("/system/addUser")
public class UserAddServlet extends HttpServlet{
	IUserService userService=new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接参
		User user=RequestBeanUtils.requestToBean(req, User.class);
		//2.调用service层的添加方法
		boolean flag=userService.addUser(user);
		//3.提示添加成功与否
		if(flag) {
			req.setAttribute("tip", "添加成功");
		}else {
			req.setAttribute("tip", "添加失败");
		}
		//4.跳转页面
		req.getRequestDispatcher("/view/system/user/userinfo_add.jsp").forward(req, resp);
	}
	
}
