package com.nm.entity;

public class Salary {
	private int salaryId;
	private int userId;
	private String salaryMonth;
	private String salaryDate;
	private float salaryBasic;
	private float salaryComm;

	private String userName;

	public int getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(int salaryId) {
		this.salaryId = salaryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSalaryMonth() {
		if (null!=salaryMonth&&!"".equals(salaryMonth)) {
			salaryMonth=salaryMonth.substring(0,7);
		}
		
		return salaryMonth;
	}

	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}

	public String getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
	}

	public float getSalaryBasic() {
		return salaryBasic;
	}

	public void setSalaryBasic(float salaryBasic) {
		this.salaryBasic = salaryBasic;
	}

	public float getSalaryComm() {
		return salaryComm;
	}

	public void setSalaryComm(float salaryComm) {
		this.salaryComm = salaryComm;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
