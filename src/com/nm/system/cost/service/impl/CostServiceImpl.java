package com.nm.system.cost.service.impl;

import java.util.List;

import com.nm.entity.Cost;
import com.nm.system.cost.dao.CostDaoImpl;
import com.nm.system.cost.dao.ICostDao;
import com.nm.system.cost.service.ICostService;

public class CostServiceImpl implements ICostService {
	ICostDao costDao=new CostDaoImpl();
	@Override
	public boolean addCost(Cost cost) {
		int rows=costDao.addCost(cost);
		if (rows>0) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteCost(Integer[] ids) {
		costDao.deleteCost(ids);

	}

	@Override
	public boolean updateCost(Cost cost) {
		int rows=costDao.updateCost(cost);
		if (rows>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Cost> queryCost(Cost cost) {
		
		return costDao.queryCost(cost);
	}

}
