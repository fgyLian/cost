package com.nm.expense.dao;

import java.util.List;

import com.nm.entity.AuditRecord;
import com.nm.entity.Detail;
import com.nm.entity.Expense;

public interface IExpenseDao {
	int addExpense(Expense expense);
	void deleteExpense(Integer ids);
	int updateExpense(Expense expense);
	List<Expense> queryExpense(Expense expense);
	List<Detail> queryDetail(int expenseId);
	int auditExpense(AuditRecord audit);
	List<AuditRecord> queryAuditRecord(int expenseId);
}
