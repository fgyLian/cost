package com.nm.finance.service;

import java.util.List;

import com.nm.entity.Salary;
import com.nm.entity.SalaryChart;

public interface ISalaryService {
	boolean salarySend(Salary salary);
	List<Salary> querySalary(Salary salary);
	List<SalaryChart> queryChart();
	
}
