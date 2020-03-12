package com.nm.system.user.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.nm.entity.Menu;
import com.nm.entity.User;
import com.nm.system.user.dao.IUserDao;
import com.nm.utils.C3p0Util;

public class UserDaoImpl implements IUserDao {

	@Override
	public int addUser(User user) {
		// 1.sql语句
		String sql="insert into t_users(roleId,userName,userSex,userAge,userPhone,userAccount,userPwd,userSalary,userMark)values(?,?,?,?,?,?,?,?,?)";
		//2.执行SQL
		try {
			return C3p0Util.update(sql, user.getRoleId(),user.getUserName(),user.getUserSex(),user.getUserAge(),user.getUserPhone(),user.getUserAccount(),user.getUserPwd(),user.getUserSalary(),"0");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteUser(User user) {
		
		String sql="update t_users set userMark=1 where userId=?";
		//涉及多条数据更新，需要开启事务操作
		Connection conn=null;
		try {
			conn=C3p0Util.getConn();
			conn.setAutoCommit(false);//设置事务不自动提交
			
			QueryRunner qr=new QueryRunner();
			for (int i = 0; i < user.getIds().length; i++) {
				qr.update(conn, sql, user.getIds()[i]);
			}
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int updateUser(User user) {
		String sql="update t_users set userName=?,userSex=?,userAge=?,userPhone=?,userPwd=?,userSalary=?,roleId=? where userId=?";
		try {
			return C3p0Util.update(sql, user.getUserName(),user.getUserSex(),user.getUserAge(),user.getUserPhone(),user.getUserPwd(),user.getUserSalary(),user.getRoleId(),user.getUserId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<User> queryUser(User user) {
		// 动态条件查询
		//1、SQL语句
		String sql ="select tu.*,tr.roleName from t_users tu ,t_role tr where  tu.roleId=tr.roleId and userMark=0";
		//sql参数集合
		List<Object> param=new ArrayList<>();
		if (user.getUserId()!=0) {
			sql+=" and tu.userId=?";
			param.add(user.getUserId());
		}
		if (user.getUserName()!=null&&!"".equals(user.getUserName())) {
			sql+=" and tu.userName like ?";
			param.add("%"+user.getUserName()+"%");
		}
		//2.执行SQL
		try {
			return C3p0Util.queryList(sql, User.class,param.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int check(String userAccount) {
		// 1.sql语句
		String sql="select count(*) from t_users where userAccount=?";
		//2.执行SQL
		try {
			return C3p0Util.queryNumber(sql, Long.class, userAccount).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public User login(User user) {
		// 1.sql语句
		String sql="select tu.*,tr.roleName from t_users tu,t_role tr where tu.roleId=tr.roleId and userAccount=? and userPwd=? and tu.userMark=0";
		//2.执行SQL
		try {
			return C3p0Util.queryOne(sql, User.class, user.getUserAccount(),user.getUserPwd());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Menu> queryMenu(int roleId) {
		// 1.SQL语句
		String sql="select * from t_role_menu trm inner join t_menu tm on trm.menuId=tm.menuId where trm.roleId=?";
		//2.执行SQL
		try {
			return C3p0Util.queryList(sql, Menu.class, roleId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
