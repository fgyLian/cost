package com.nm.finance.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.entity.User;
import com.nm.system.user.service.IUserService;
import com.nm.system.user.service.impl.UserServiceImpl;

import net.sf.json.JSONObject;

@WebServlet("/finance/basicSalaryQuery")
public class UserBasicSalaryQueryServlet extends HttpServlet{
	IUserService userService=new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user=RequestBeanUtils.requestToBean(req, User.class);
		user=userService.queryUser(user).get(0);
		float basicSalary=user.getUserSalary();
		//返回json格式
		JSONObject obj=new JSONObject();
		obj.put("basicSalary",basicSalary);
		PrintWriter out=resp.getWriter();
		out.println(obj);
		out.close();
	}
	
}
