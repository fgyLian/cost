package com.nm.entity;

import java.util.Arrays;
import java.util.Date;

public class Expense {
	//报销单
	private int expenseId;
	private int userId;
	private String expenseName;
	private String expenseDesc;
	private float expenseTotal;
	private Date expenseDate;
	private String expenseState;
	//报销明细
	private Integer[] costIds;
	private Float[] detailMoneys;
	private String[] detailDescs;
	//查询条件
	private String userName;
	private String startDate;
	private String endDate;
	
	private String oparate;
	
	
	public String getOparate() {
		return oparate;
	}
	public void setOparate(String oparate) {
		this.oparate = oparate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public String getExpenseDesc() {
		return expenseDesc;
	}
	public void setExpenseDesc(String expenseDesc) {
		this.expenseDesc = expenseDesc;
	}
	public float getExpenseTotal() {
		return expenseTotal;
	}
	public void setExpenseTotal(float expenseTotal) {
		this.expenseTotal = expenseTotal;
	}
	public Date getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getExpenseState() {
		return expenseState;
	}
	public void setExpenseState(String expenseState) {
		this.expenseState = expenseState;
	}
	public Integer[] getCostIds() {
		return costIds;
	}
	public void setCostIds(Integer[] costIds) {
		this.costIds = costIds;
	}
	public Float[] getDetailMoneys() {
		return detailMoneys;
	}
	public void setDetailMoneys(Float[] detailMoneys) {
		this.detailMoneys = detailMoneys;
	}
	public String[] getDetailDescs() {
		return detailDescs;
	}
	public void setDetailDescs(String[] detailDescs) {
		this.detailDescs = detailDescs;
	}
	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", userId=" + userId + ", expenseName=" + expenseName
				+ ", expenseDesc=" + expenseDesc + ", expenseTotal=" + expenseTotal + ", expenseDate=" + expenseDate
				+ ", expenseState=" + expenseState + ", costIds=" + Arrays.toString(costIds) + ", detailMoneys="
				+ Arrays.toString(detailMoneys) + ", detailDescs=" + Arrays.toString(detailDescs) + ", userName="
				+ userName + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	
	
}
