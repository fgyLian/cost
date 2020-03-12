package com.nm.system.user.dao;

import java.util.List;

import com.nm.entity.Menu;
import com.nm.entity.User;

public interface IUserDao {
	int addUser(User user);
	int deleteUser(User user);
	int updateUser(User user);
	List<User> queryUser(User user);
	int check(String userAccount);
	User login(User user);
	List<Menu> queryMenu(int roleId);
}
