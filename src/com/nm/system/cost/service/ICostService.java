package com.nm.system.cost.service;

import java.util.List;

import com.nm.entity.Cost;

public interface ICostService {
	boolean addCost(Cost cost);
	void deleteCost(Integer[] ids);
	boolean updateCost(Cost cost);
	List<Cost> queryCost(Cost cost);
}
