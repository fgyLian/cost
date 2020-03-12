package com.nm.expense.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.entity.AuditRecord;
import com.nm.entity.Detail;
import com.nm.entity.Expense;
import com.nm.expense.service.IExpenseService;
import com.nm.expense.service.impl.ExpenseServiceImpl;

@WebServlet("/expense/showExpenseDetail")
public class ShowExpenseDetailServlet extends HttpServlet{
	IExpenseService expenseService=new ExpenseServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		//查询报销单主体信息
	    expense=expenseService.queryExpense(expense).get(0);
	    //查询明细信息
	    List<Detail> detailList=expenseService.queryDetail(expense.getExpenseId());
	    //审核记录
	    List<AuditRecord> recordList=expenseService.queryAuditRecord(expense.getExpenseId());
	    req.setAttribute("expense", expense);
	    req.setAttribute("detailList", detailList);
	    req.setAttribute("recordList", recordList);
		req.getRequestDispatcher("/view/expense/expense/expense_show.jsp").forward(req, resp);
	}
	
}
