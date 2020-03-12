package com.nm.entity;

public class Menu {
	private int menuId;
	private String menuName;
	private int pMenuId;
	private String menuUrl;
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getpMenuId() {
		return pMenuId;
	}
	public void setpMenuId(int pMenuId) {
		this.pMenuId = pMenuId;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", pMenuId=" + pMenuId + ", menuUrl=" + menuUrl
				+ "]";
	}
	
}
