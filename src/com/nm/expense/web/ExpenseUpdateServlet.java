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
import com.nm.entity.Detail;
import com.nm.entity.Expense;
import com.nm.expense.service.IExpenseService;
import com.nm.expense.service.impl.ExpenseServiceImpl;
import com.nm.system.cost.service.ICostService;
import com.nm.system.cost.service.impl.CostServiceImpl;

@WebServlet("/expense/updateExpense")
public class ExpenseUpdateServlet extends HttpServlet{
	IExpenseService expenseService=new ExpenseServiceImpl();
	ICostService costService=new CostServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//报销的主体信息
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		expense=expenseService.queryExpense(expense).get(0);
		//报销明细信息
		List<Detail> detailList=expenseService.queryDetail(expense.getExpenseId());
		//费用信息
		List<Cost> costList=costService.queryCost(new Cost());
		//返回
		req.setAttribute("expense", expense);
		req.setAttribute("detailList", detailList);
		req.setAttribute("costList", costList);
		req.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接受修改后的报销单主体信息
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		boolean flag=expenseService.updateExpense(expense);
		
		if (flag) {
			req.setAttribute("tip", "修改成功");
		} else {
			req.setAttribute("tip", "修改失败");
		}
		//返显
		expense=expenseService.queryExpense(expense).get(0);
		//报销明细信息
		List<Detail> detailList=expenseService.queryDetail(expense.getExpenseId());
		//费用信息
		List<Cost> costList=costService.queryCost(new Cost());
		//返回
		req.setAttribute("expense", expense);
		req.setAttribute("detailList", detailList);
		req.setAttribute("costList", costList);
		req.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(req, resp);
	}
	
}
