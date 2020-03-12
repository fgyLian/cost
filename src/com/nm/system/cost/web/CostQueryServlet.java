package com.nm.system.cost.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.entity.Cost;
import com.nm.system.cost.service.ICostService;
import com.nm.system.cost.service.impl.CostServiceImpl;
@WebServlet("/system/costQuery")
public class CostQueryServlet extends HttpServlet{
	ICostService costService=new CostServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接参
		Cost cost = RequestBeanUtils.requestToBean(req, Cost.class);
		// 调用service
		List<Cost> costList=costService.queryCost(cost);
		// 返回
		req.setAttribute("costList", costList);
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("/view/system/cost/cost_list.jsp").forward(req, resp);
	}
	
}
