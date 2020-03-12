package com.nm.expense.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.nm.entity.AuditRecord;
import com.nm.entity.Detail;
import com.nm.entity.Expense;
import com.nm.expense.dao.IExpenseDao;
import com.nm.utils.C3p0Util;

public class ExpenseDaoImpl implements IExpenseDao {

	@Override
	public int addExpense(Expense expense) {
		Connection conn = null;
		try {
			conn = C3p0Util.getConn();
			conn.setAutoCommit(false);
			QueryRunner qr=new QueryRunner();
			// 1.添加报销单主体信息
			String sql1="insert into t_expense(userId,expenseName,expenseDesc,expenseTotal,expenseDate,expenseState)values(?,?,?,?,now(),?)";
			int rows=qr.update(conn,sql1,expense.getUserId(),expense.getExpenseName(),expense.getExpenseDesc(),expense.getExpenseTotal(),expense.getExpenseState());
			// 2.添加报销明细
			//查询刚才添加的报销单编号
			String sql2="select last_insert_id()";
			BigInteger expenseId=qr.query(conn, sql2,new ScalarHandler<BigInteger>());
			
			String sql3="insert into t_expense_detail(expenseId,costId,detailDesc,detailMoney)values(?,?,?,?)";
			Integer[] costIds=expense.getCostIds();
			String[] detailDescs=expense.getDetailDescs();
			Float[] detailMoneys=expense.getDetailMoneys();
			for (int i = 0; i < costIds.length; i++) {
				rows+=qr.update(conn,sql3,expenseId,costIds[i],detailDescs[i],detailMoneys[i]);
			}
			conn.commit();
			conn.setAutoCommit(true);
			return rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return 0;
	}

	@Override
	public void deleteExpense(Integer ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public int updateExpense(Expense expense) {
		Connection conn=null;
		try {
			conn=C3p0Util.getConn();
			conn.setAutoCommit(false);
			QueryRunner qr=new QueryRunner();
			//1.修改报销单主体信息
			String sql1="update t_expense set expenseName=?,expenseTotal=?,expenseDesc=?,expenseState=? where expenseId=?";
			int rows=qr.update(conn, sql1, expense.getExpenseName(),expense.getExpenseTotal(),expense.getExpenseDesc(),expense.getExpenseState(),expense.getExpenseId());
			//2.删除报销明细信息
			String sql2="delete from t_expense_detail where expenseId=?";
			rows+=qr.update(conn, sql2, expense.getExpenseId());
			//3.循环重新添加报销明细信息
			String sql3="insert into t_expense_detail(expenseId,costId,detailDesc,detailMoney)values(?,?,?,?)";
			Integer[] costIds=expense.getCostIds();
			String[] detailDescs=expense.getDetailDescs();
			Float[] detailMoneys=expense.getDetailMoneys();
			for (int i = 0; i < costIds.length; i++) {
				rows+=qr.update(conn,sql3,expense.getExpenseId(),costIds[i],detailDescs[i],detailMoneys[i]);
			}
			conn.commit();
			conn.setAutoCommit(true);
			return rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public List<Expense> queryExpense(Expense expense) {
		StringBuffer sql=new StringBuffer("select te.*,tu.userName from t_expense te inner join t_users tu where te.userId=tu.userId");
		List<Object> params=new ArrayList<>();
		if(expense.getExpenseId()!=0) {
			sql.append(" and te.expenseId=?");
			params.add(expense.getExpenseId());
		}
		if(expense.getUserId()!=0) {
			sql.append(" and tu.userId=?");
			params.add(expense.getUserId());
		}
		if(expense.getExpenseName()!=null&&!"".equals(expense.getExpenseName())) {
			sql.append(" and te.expenseName like ?");
			params.add("%"+expense.getExpenseName()+"%");
		}
		if(expense.getUserName()!=null&&!"".equals(expense.getUserName())) {
			sql.append(" and tu.userName like ?");
			params.add("%"+expense.getUserName()+"%");
		}
		if(expense.getExpenseState()!=null&&!"".equals(expense.getExpenseState())) {
			sql.append(" and te.expenseState = ?");
			params.add(expense.getExpenseState());
		}
		if(expense.getStartDate()!=null&&!"".equals(expense.getStartDate())) {
			sql.append(" and te.startDate() >= ?");
			params.add(expense.getStartDate());
		}
		if(expense.getEndDate()!=null&&!"".equals(expense.getEndDate())) {
			sql.append(" and te.endDate() <= ?");
			params.add(expense.getEndDate());
		}
		try {
			return C3p0Util.queryList(sql.toString(), Expense.class, params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	@Override
	public List<Detail> queryDetail(int expenseId) {
		String sql="select ted.*, tc.costName from t_expense_detail ted,t_cost tc where tc.costId=ted.costId and expenseId=?";
		try {
			return C3p0Util.queryList(sql, Detail.class, expenseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int auditExpense(AuditRecord audit) {
		Connection conn=null;
		try {
			conn=C3p0Util.getConn();
			conn.setAutoCommit(false);
			
			QueryRunner qr=new QueryRunner();
			//1.添加审核记录
			String sql1="insert into t_audit_record(userId,expenseId,auditState,auditSugg,auditDate)values(?,?,?,?,now())";
			int rows=qr.update(conn, sql1, audit.getUserId(),audit.getExpenseId(),audit.getAuditState(),audit.getAuditSugg());
			//2.修改报销单的状态
			String sql2="update t_expense set expenseState=? where expenseId=?";
			rows+=qr.update(conn, sql2, audit.getAuditState(),audit.getExpenseId());
			conn.commit();
			conn.setAutoCommit(true);
			return rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public List<AuditRecord> queryAuditRecord(int expenseId) {
		String sql="select tad.*,tu.userName from t_audit_record tad,t_users tu where tu.userId=tad.userId and tad.expenseId=?";
		try {
			return C3p0Util.queryList(sql, AuditRecord.class, expenseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

}
