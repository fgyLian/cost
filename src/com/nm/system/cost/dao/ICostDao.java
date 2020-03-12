package com.nm.system.cost.dao;

import java.util.List;

import com.nm.entity.Cost;

public interface ICostDao {
	int addCost(Cost cost);
	void deleteCost(Integer[] ids);
	int updateCost(Cost cost);
	List<Cost> queryCost(Cost cost);
}
