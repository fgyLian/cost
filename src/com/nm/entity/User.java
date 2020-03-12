package com.nm.entity;

public class User {
	private int userId;
	private int roleId;
	private String userName;
	private String userSex;
	private int userAge;
	private String userPhone;
	private String userAccount;
	private String userPwd;
	private float userSalary;
	private String userMark;
	
	private String roleName;
	
	
	private String ck;
	private Integer[] ids;
	
	
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	public String getCk() {
		ck="<input type='checkbox' name='ids' value='"+this.getUserId()+"'>";
		return ck;
	}
	public void setCk(String ck) {
		this.ck = ck;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public float getUserSalary() {
		return userSalary;
	}
	public void setUserSalary(float userSalary) {
		this.userSalary = userSalary;
	}
	public String getUserMark() {
		return userMark;
	}
	public void setUserMark(String userMark) {
		this.userMark = userMark;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", roleId=" + roleId + ", userName=" + userName + ", userSex=" + userSex
				+ ", userAge=" + userAge + ", userPhone=" + userPhone + ", userAccount=" + userAccount + ", userPwd="
				+ userPwd + ", userSalary=" + userSalary + ", userMark=" + userMark + "]";
	}
	
	
}
