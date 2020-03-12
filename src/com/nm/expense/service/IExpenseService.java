package com.nm.expense.service;

import java.util.List;

import com.nm.entity.AuditRecord;
import com.nm.entity.Detail;
import com.nm.entity.Expense;

public interface IExpenseService {
	boolean addExpense(Expense expense);
	void deleteExpense(Integer ids);
	boolean updateExpense(Expense expense);
	List<Expense> queryExpense(Expense expense);
	List<Detail> queryDetail(int expenseId);
	boolean auditExpense(AuditRecord audit);
	List<AuditRecord> queryAuditRecord(int expenseId);
}
