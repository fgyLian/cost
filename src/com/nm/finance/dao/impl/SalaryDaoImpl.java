package com.nm.finance.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nm.entity.Salary;
import com.nm.entity.SalaryChart;
import com.nm.finance.dao.ISalaryDao;
import com.nm.utils.C3p0Util;

public class SalaryDaoImpl implements ISalaryDao {

	@Override
	public int salarySend(Salary salary) {
		String sql="insert into t_salary_record(userId,salaryMonth,salaryDate,salaryBasic,salaryComm)values(?,?,now(),?,?)";
		String salaryMonth=salary.getSalaryMonth();
		try {
			return C3p0Util.update(sql, salary.getUserId(),salaryMonth+"-01",salary.getSalaryBasic(),salary.getSalaryComm());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Salary> querySalary(Salary salary) {
		StringBuffer sql=new StringBuffer("select tsr.*, tu.userName from t_salary_record tsr,t_users tu where tu.userId=tsr.userId");
		List<Object> params=new ArrayList<>();
		if(salary.getUserId()!=0) {
			sql.append(" and tu.userId=?");
			params.add(salary.getUserId());
		}
		if(salary.getUserName()!=null&&!"".equals(salary.getUserName())) {
			sql.append(" and tu.userName like ?");
			params.add("%"+salary.getUserName()+"%");
		}
		if(salary.getSalaryMonth()!=null&&!"".equals(salary.getSalaryMonth())) {
			sql.append(" and tsr.salaryMonth like ?");
			params.add(salary.getSalaryMonth()+"%");
		}
		try {
			return C3p0Util.queryList(sql.toString(), Salary.class, params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SalaryChart> queryChart() {
		String sql="select tsr.salaryMonth,sum(tsr.salaryBasic) as salaryBasicTotal,sum(tsr.salaryComm) as salaryCommTotal from t_salary_record tsr group by tsr.salaryMonth";
		try {
			return C3p0Util.queryList(sql, SalaryChart.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
