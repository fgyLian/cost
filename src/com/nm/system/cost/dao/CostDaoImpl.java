package com.nm.system.cost.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.nm.entity.Cost;
import com.nm.utils.C3p0Util;

public class CostDaoImpl implements ICostDao {

	@Override
	public int addCost(Cost cost) {
		String sql="insert into t_cost(costName,costDesc,costMark)values(?,?,?)";
		try {
			return C3p0Util.update(sql, cost.getCostName(),cost.getCostDesc(),"0");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void deleteCost(Integer[] ids) {
		String sql="update t_cost set costMark=1 where costId=?";
		
		Connection conn=null;
		try {
			conn=C3p0Util.getConn();
			conn.setAutoCommit(false);
			
			QueryRunner qr=new QueryRunner();
			for (int i = 0; i < ids.length; i++) {
				qr.update(conn,sql,ids[i]);
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
	}

	@Override
	public int updateCost(Cost cost) {
		String sql="update t_cost set costName=?,costDesc=? where costId=?";
		try {
			return C3p0Util.update(sql, cost.getCostName(),cost.getCostDesc(),cost.getCostId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Cost> queryCost(Cost cost) {
		StringBuffer sql=new StringBuffer("select * from t_cost where costMark=0");
		//拼接查询条件
		List<Object> param=new ArrayList<>();
		if (cost.getCostId()!=0) {
			sql.append(" and costId=?");
			param.add(cost.getCostId());
		}
		if (cost.getCostName()!=null && !"".equals(cost.getCostName())) {
			sql.append(" and costName like ?");
			param.add("%"+cost.getCostName()+"%");
		}
		try {
			return C3p0Util.queryList(sql.toString(), Cost.class, param.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
