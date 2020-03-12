package com.nm.finance.web;

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

@WebServlet("/finance/financeQuery")
public class FinanceQueryServlet extends HttpServlet {
	IExpenseService expenseService = new ExpenseServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 查询报销单状态为2（经理审核通过）的报销单
		Expense expense = RequestBeanUtils.requestToBean(req, Expense.class);
		expense.setExpenseState("2");

		List<Expense> expenseList = expenseService.queryExpense(expense);

		req.setAttribute("expenseList", expenseList);
		req.setAttribute("expense", expense);
		req.getRequestDispatcher("/view/finance/financAaudit/financeaudit_list.jsp").forward(req, resp);
	}

}
