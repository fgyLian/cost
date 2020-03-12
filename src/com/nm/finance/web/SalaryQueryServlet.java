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
import com.nm.finance.service.ISalaryService;
import com.nm.finance.service.impl.SalaryServiceImpl;

@WebServlet("/finance/salaryQuery")
public class SalaryQueryServlet extends HttpServlet{
	ISalaryService salaryService=new SalaryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Salary salary=RequestBeanUtils.requestToBean(req, Salary.class);
		List<Salary> salaryList=salaryService.querySalary(salary);
		req.setAttribute("salaryList", salaryList);
		req.setAttribute("salary", salary);
		req.getRequestDispatcher("/view/finance/salary/salarypayment_list.jsp").forward(req, resp);
	}
	

}
