package com.nm.expense.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.entity.Cost;
import com.nm.entity.Expense;
import com.nm.expense.service.IExpenseService;
import com.nm.expense.service.impl.ExpenseServiceImpl;
import com.nm.system.cost.service.ICostService;
import com.nm.system.cost.service.impl.CostServiceImpl;

@WebServlet("/expense/expenseAdd")
public class ExpenseAddServlet extends HttpServlet{
	ICostService costService=new CostServiceImpl();
	IExpenseService expenseService=new ExpenseServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 跳转页面
		Cost cost=new Cost();
		List<Cost> costList=costService.queryCost(cost);
		req.setAttribute("costList",costList);
		req.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 添加操作
		//获取报销单信息和报销明细信息
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		boolean flag=expenseService.addExpense(expense);
		if (flag) {
			req.setAttribute("tip", "添加成功");
		} else {
			req.setAttribute("tip", "添加失败");
		}
		Cost cost=new Cost();
		List<Cost> costList=costService.queryCost(cost);
		req.setAttribute("costList",costList);
		req.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(req, resp);
	}
	
}
