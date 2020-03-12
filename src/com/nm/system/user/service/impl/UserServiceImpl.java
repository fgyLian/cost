package com.nm.system.user.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.nm.entity.Menu;
import com.nm.entity.User;
import com.nm.system.user.dao.IUserDao;
import com.nm.system.user.dao.impl.UserDaoImpl;
import com.nm.system.user.service.IUserService;
import com.nm.utils.C3p0Util;

public  class UserServiceImpl implements IUserService {
	IUserDao userDao=new UserDaoImpl();
	@Override
	public boolean addUser(User user) {
		int rows=userDao.addUser(user);
		if (rows>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		int rows=userDao.deleteUser(user);
		if (rows>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		int rows=userDao.updateUser(user);
		if (rows>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> queryUser(User user) {
		
		return userDao.queryUser(user);
	}

	@Override
	public boolean check(String userAccount) {
		int count=userDao.check(userAccount);
		if (count==0) {
			return true;
		}
		return false;
	}

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public List<Menu> queryMenu(int roleId) {
		return userDao.queryMenu(roleId);
	}

	

}
