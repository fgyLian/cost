package com.nm.finance.service.impl;

import java.util.List;

import com.nm.entity.Salary;
import com.nm.entity.SalaryChart;
import com.nm.finance.dao.ISalaryDao;
import com.nm.finance.dao.impl.SalaryDaoImpl;
import com.nm.finance.service.ISalaryService;

public class SalaryServiceImpl implements ISalaryService {
	ISalaryDao salaryDao=new SalaryDaoImpl();
	@Override
	public boolean salarySend(Salary salary) {
		int rows=salaryDao.salarySend(salary);
		if (rows>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Salary> querySalary(Salary salary) {
		
		return salaryDao.querySalary(salary);
	}

	@Override
	public List<SalaryChart> queryChart() {
		
		return salaryDao.queryChart();
	}

}
