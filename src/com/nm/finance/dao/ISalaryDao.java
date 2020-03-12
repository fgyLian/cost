package com.nm.finance.dao;

import java.util.List;

import com.nm.entity.Salary;
import com.nm.entity.SalaryChart;

public interface ISalaryDao {
	int salarySend(Salary salary);
	List<Salary> querySalary(Salary salary);
	List<SalaryChart> queryChart();
}
