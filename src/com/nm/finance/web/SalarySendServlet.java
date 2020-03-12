package com.nm.finance.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.entity.Salary;
import com.nm.entity.User;
import com.nm.finance.service.ISalaryService;
import com.nm.finance.service.impl.SalaryServiceImpl;
import com.nm.system.user.service.IUserService;
import com.nm.system.user.service.impl.UserServiceImpl;

@WebServlet("/finance/sendSalary")
public class SalarySendServlet extends HttpServlet {
	IUserService userService = new UserServiceImpl();
	ISalaryService salaryService=new SalaryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 显示所有薪资领取人
		// 调用service层的查询方法
		List<User> userList = userService.queryUser(new User());
		req.setAttribute("userList", userList);
		req.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接受薪资信息
		Salary salary=RequestBeanUtils.requestToBean(req, Salary.class);
		boolean flag=salaryService.salarySend(salary);
		if (flag) {
			req.setAttribute("tip", "薪资发放成功");
		} else {
			req.setAttribute("tip", "薪资发放失败");
		}
		//回显领取人
		List<User> userList = userService.queryUser(new User());
		req.setAttribute("userList", userList);
		
		req.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(req, resp);
	}

}
