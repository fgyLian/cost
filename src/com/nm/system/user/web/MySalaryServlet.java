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
import com.nm.entity.Salary;
import com.nm.entity.User;
import com.nm.finance.service.ISalaryService;
import com.nm.finance.service.impl.SalaryServiceImpl;

@WebServlet("/system/mySalary")
public class MySalaryServlet extends HttpServlet{
	ISalaryService salaryService=new SalaryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接参
		Salary salary=RequestBeanUtils.requestToBean(req, Salary.class);
		//我的id
		HttpSession session=req.getSession();
		User user=(User) session.getAttribute("userInfo");
		salary.setUserId(user.getUserId());
		//调用service
		List<Salary> salaryList=salaryService.querySalary(salary);
		//回显
		req.setAttribute("salaryList", salaryList);
		req.setAttribute("salary", salary);
		req.getRequestDispatcher("/view/system/user/salarypayment_mylist.jsp").forward(req, resp);
	}
	
}
