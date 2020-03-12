package com.nm.entity;

public class SalaryChart {
	private String salaryMonth;
	private float salaryBasicTotal;
	private float salaryCommTotal;

	public String getSalaryMonth() {
		if (null!=salaryMonth&&!"".equals(salaryMonth)) {
			salaryMonth=salaryMonth.substring(0,7);
		}
		return salaryMonth;
	}

	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}

	public float getSalaryBasicTotal() {
		return salaryBasicTotal;
	}

	public void setSalaryBasicTotal(float salaryBasicTotal) {
		this.salaryBasicTotal = salaryBasicTotal;
	}

	public float getSalaryCommTotal() {
		return salaryCommTotal;
	}

	public void setSalaryCommTotal(float salaryCommTotal) {
		this.salaryCommTotal = salaryCommTotal;
	}

	@Override
	public String toString() {
		return "SalaryChart [salaryMonth=" + salaryMonth + ", salaryBasicTotal=" + salaryBasicTotal
				+ ", salaryCommTotal=" + salaryCommTotal + "]";
	}

}
