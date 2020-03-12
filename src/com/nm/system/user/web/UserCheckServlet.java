package com.nm.system.user.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nm.system.user.service.IUserService;
import com.nm.system.user.service.impl.UserServiceImpl;

import net.sf.json.JSONObject;
@WebServlet("/system/checkUser")
public class UserCheckServlet extends HttpServlet{
	IUserService userService=new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.接参
		String userAccount= req.getParameter("userAccount");
		//2.调用service的验证方法
		boolean flag=userService.check(userAccount);
		//3.返回
		PrintWriter out=resp.getWriter();
		JSONObject obj=new JSONObject();
		if (flag) {
			obj.put("state", true);
			obj.put("tip", "账号可用");
		}else {
			obj.put("state", false);
			obj.put("tip", "账号已存在");
		}
		out.print(obj);
		out.close();
	}
	
}
