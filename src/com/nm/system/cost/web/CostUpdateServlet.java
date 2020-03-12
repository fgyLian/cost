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

@WebServlet("/system/costUpdate")
public class CostUpdateServlet extends HttpServlet {
	ICostService costService = new CostServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 跳转修改页面
		Cost cost = RequestBeanUtils.requestToBean(req, Cost.class);
		cost=costService.queryCost(cost).get(0);
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接参
		Cost cost = RequestBeanUtils.requestToBean(req, Cost.class);
		// 调用service
		boolean flag = costService.updateCost(cost);
		// 返回
		if (flag) {
			req.setAttribute("tip", "修改成功");
		} else {
			req.setAttribute("tip", "修改失败");
		}
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(req, resp);
	}

}
