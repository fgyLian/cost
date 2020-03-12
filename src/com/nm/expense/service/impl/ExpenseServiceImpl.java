package com.nm.expense.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.nm.entity.AuditRecord;
import com.nm.entity.Detail;
import com.nm.entity.Expense;
import com.nm.expense.dao.IExpenseDao;
import com.nm.expense.dao.impl.ExpenseDaoImpl;
import com.nm.expense.service.IExpenseService;


public class ExpenseServiceImpl implements IExpenseService {
	IExpenseDao expenseDao = new ExpenseDaoImpl();

	@Override
	public boolean addExpense(Expense expense) {
		int rows = expenseDao.addExpense(expense);
		if (rows > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteExpense(Integer ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateExpense(Expense expense) {
		int rows=expenseDao.updateExpense(expense);
		if (rows>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Expense> queryExpense(Expense expense) {
		// TODO Auto-generated method stub
		List<Expense> list = expenseDao.queryExpense(expense);
		List<Expense> newList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Expense ex = list.get(i);
			if (ex.getExpenseState().equals("0")) {
				ex.setExpenseState("<input type='bottom' class='btn btn-primary' value='未提交审核'");
				ex.setOparate("<a href='expense/updateExpense?expenseId="+ex.getExpenseId()+"'>修改<a/>");
			} else if (ex.getExpenseState().equals("1")) {
				ex.setExpenseState("<input type='bottom' class='btn btn-info' value='待经理审核'");
				ex.setOparate("<a href='expense/showExpenseDetail?expenseId="+ex.getExpenseId()+"'>查看详情</a>");
			} else if (ex.getExpenseState().equals("2")) {
				ex.setExpenseState("<input type='bottom' class='btn btn-success' value='经理审核通过'");
				ex.setOparate("<a href='expense/showExpenseDetail?expenseId="+ex.getExpenseId()+"'>查看详情</a>");
			} else if (ex.getExpenseState().equals("3")) {
				ex.setExpenseState("<input type='bottom' class='btn btn-success' value='财务审核通过'");
				ex.setOparate("<a href='expense/showExpenseDetail?expenseId="+ex.getExpenseId()+"'>查看详情</a>");
			} else if (ex.getExpenseState().equals("-2")) {
				ex.setExpenseState("<input type='bottom' class='btn btn-danger' value='经理审核未通过'");
				ex.setOparate("<a href='expense/showExpenseDetail?expenseId="+ex.getExpenseId()+"'>查看详情</a>");
			} else if (ex.getExpenseState().equals("-3")) {
			ex.setExpenseState("<input type='bottom' class='btn btn-danger' value='财务审核未通过'");
			ex.setOparate("<a href='expense/showExpenseDetail?expenseId="+ex.getExpenseId()+"'>查看详情</a>");
			}
			newList.add(ex);
		}
		return newList;
	}


	@Override
	public List<Detail> queryDetail(int expenseId) {
		return expenseDao.queryDetail(expenseId);
	}

	@Override
	public boolean auditExpense(AuditRecord audit) {
		int rows = expenseDao.auditExpense(audit);
		if (rows > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<AuditRecord> queryAuditRecord(int expenseId) {
		List<AuditRecord> list=expenseDao.queryAuditRecord(expenseId);
		List<AuditRecord> newList = new ArrayList<>();
		
			for (AuditRecord auditRecord : list) {
				if (auditRecord.getAuditState().equals("1")) {
					auditRecord.setAuditState("<input type='bottom' class='btn btn-info' value='待经理审核'");
				} else if (auditRecord.getAuditState().equals("2")) {
					auditRecord.setAuditState("<input type='bottom' class='btn btn-success' value='经理审核通过'");
				} else if (auditRecord.getAuditState().equals("3")) {
					auditRecord.setAuditState("<input type='bottom' class='btn btn-success' value='财务审核通过'");
				} else if (auditRecord.getAuditState().equals("-2")) {
					auditRecord.setAuditState("<input type='bottom' class='btn btn-danger' value='经理审核未通过'");
				} else if (auditRecord.getAuditState().equals("-3")) {
					auditRecord.setAuditState("<input type='bottom' class='btn btn-danger' value='财务审核未通过'");
				}
				newList.add(auditRecord);
			}
		return newList;
	}

	

	
}
