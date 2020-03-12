package com.nm.system.user.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.entity.Menu;
import com.nm.entity.SalaryChart;
import com.nm.entity.User;
import com.nm.finance.service.ISalaryService;
import com.nm.finance.service.impl.SalaryServiceImpl;
import com.nm.system.user.service.IUserService;
import com.nm.system.user.service.impl.UserServiceImpl;
@WebServlet("/system/doLogin")
public class UserLoginServlet extends HttpServlet{
	IUserService userService=new UserServiceImpl();
	ISalaryService salaryService=new SalaryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接参
		User user =RequestBeanUtils.requestToBean(req, User.class);
		//2.调用service层登录方法
		User userInfo=userService.login(user);
		//3.跳转页面
		if (userInfo==null) {//登录失败
			req.setAttribute("tip", "用户名或密码错误");
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		}else {//登录成功
			//把用户信息保存到session
			HttpSession session=req.getSession();
			session.setAttribute("userInfo", userInfo);
			//根据角色编号查询角色的权限
			List<Menu> menuList=userService.queryMenu(userInfo.getRoleId());
			session.setAttribute("menuList", menuList);
			
			
			//查询薪资报表信息
			if (userInfo.getRoleId()==4) {
				List<SalaryChart> salaryChart=salaryService.queryChart();
				session.setAttribute("salaryChart", salaryChart);
			}else {
				List<SalaryChart> salaryChart=null;
				session.setAttribute("salaryChart", salaryChart);
			}
			
			req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
		}
	}
	
}
