package com.nm.expense.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.entity.Expense;
import com.nm.expense.service.IExpenseService;
import com.nm.expense.service.impl.ExpenseServiceImpl;

@WebServlet("/expense/expenseList")
public class ExpenseListServlet extends HttpServlet{
	IExpenseService expenseService=new ExpenseServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		List<Expense> expenseList=expenseService.queryExpense(expense);
		
		req.setAttribute("expenseList", expenseList);
		req.setAttribute("expense", expense);
		
		req.getRequestDispatcher("/view/expense/expense/expense_list.jsp").forward(req, resp);
	
	}
	
}
