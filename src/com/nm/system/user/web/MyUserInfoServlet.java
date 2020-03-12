package com.nm.system.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.entity.User;
import com.nm.system.user.service.IUserService;
import com.nm.system.user.service.impl.UserServiceImpl;

@WebServlet("/system/myInfo")
public class MyUserInfoServlet extends HttpServlet {
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 跳转个人信息页面
		req.getRequestDispatcher("/view/system/user/myinfo_show.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 个人信息修改
		// 接参
		User user = RequestBeanUtils.requestToBean(req, User.class);
		// 调用
		boolean flag = userService.updateUser(user);
		// 3.跳转，返回
		if (flag) {
			req.setAttribute("tip", "修改成功");
			HttpSession session=req.getSession();
			session.setAttribute("userInfo", user);
		} else {
			req.setAttribute("tip", "修改失败");
		}
		
		req.getRequestDispatcher("/view/system/user/myinfo_show.jsp").forward(req, resp);

	}

}
