package com.nm.system.cost.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.entity.Cost;
import com.nm.system.cost.service.ICostService;
import com.nm.system.cost.service.impl.CostServiceImpl;

@WebServlet("/system/costDelete")
public class CostDeleteServlet extends HttpServlet {
	ICostService costService = new CostServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接参
		Cost cost = RequestBeanUtils.requestToBean(req, Cost.class);
		// 调用service
		costService.deleteCost(cost.getIds());
		//刷新页面
		req.getRequestDispatcher("/system/costQuery").forward(req, resp);
	}

}
