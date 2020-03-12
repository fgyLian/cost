package com.nm.expense.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.entity.Expense;
import com.nm.entity.User;
import com.nm.expense.service.IExpenseService;
import com.nm.expense.service.impl.ExpenseServiceImpl;

@WebServlet("/expense/myExpense")
public class MyExpenseServlet extends HttpServlet{
	IExpenseService expenseService=new ExpenseServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		HttpSession session=req.getSession();
		User user=(User) session.getAttribute("userInfo");
		expense.setUserId(user.getUserId());
		
		List<Expense> expenseList=expenseService.queryExpense(expense);
		
		req.setAttribute("expenseList", expenseList);
		req.getRequestDispatcher("/view/expense/expense/expense_mylist.jsp").forward(req, resp);
	}
	
}
