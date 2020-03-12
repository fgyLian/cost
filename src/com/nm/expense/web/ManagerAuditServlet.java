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

@WebServlet("/expense/managerAudit")
public class ManagerAuditServlet extends HttpServlet{
	IExpenseService expenseService=new ExpenseServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		//1.查询报销单主体信息
	    expense=expenseService.queryExpense(expense).get(0);
	    //2.查询明细信息
	    List<Detail> detailList=expenseService.queryDetail(expense.getExpenseId());
	    //3.查询审核记录
	    List<AuditRecord> recordList=expenseService.queryAuditRecord(expense.getExpenseId());

	    req.setAttribute("expense", expense);
	    req.setAttribute("detailList", detailList);
	    req.setAttribute("recordList", recordList);
		req.getRequestDispatcher("/view/expense/managerAudit/expense_audit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.接受审核记录参数
		AuditRecord audit=RequestBeanUtils.requestToBean(req, AuditRecord.class);
		//2.调用service
		boolean flag=expenseService.auditExpense(audit);
		//3.返回
		//3-1报销单信息
		Expense expense=new Expense();
		expense.setExpenseId(audit.getExpenseId());
		expenseService.queryExpense(expense);
		//3-2报销明细信息
		List<Detail> detailList=expenseService.queryDetail(expense.getExpenseId());
		//3-3审核记录
		List<AuditRecord> recordList=expenseService.queryAuditRecord(expense.getExpenseId());
		req.setAttribute("expense", expense);
	    req.setAttribute("detailList", detailList);
	    req.setAttribute("recordList", recordList);
	   
		
		
		if (flag) {
			req.setAttribute("tip", "审核成功");
			
		} else {
			req.setAttribute("tip", "审核失败");
		}
		
		req.getRequestDispatcher("/view/expense/managerAudit/expense_audit.jsp").forward(req, resp);
	}
	
}
