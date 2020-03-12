package com.nm.system.user.service;

import java.util.List;

import com.nm.entity.Menu;
import com.nm.entity.User;

public interface IUserService {
	boolean addUser(User user);
	boolean deleteUser(User user);
	boolean updateUser(User user);
	List<User> queryUser(User user);
	boolean check(String userAccount);
	User login(User user);
	List<Menu> queryMenu(int roleId);
}
