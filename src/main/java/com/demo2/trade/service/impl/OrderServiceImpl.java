/*
 * created by 2019年7月23日 下午4:23:12
 */
package com.demo2.trade.service.impl;

import java.util.List;

import com.demo2.support.dao.BasicDao;
import com.demo2.trade.entity.Order;
import com.demo2.trade.service.OrderService;

/**
 * @author fangang
 */
public class OrderServiceImpl implements OrderService {
	private BasicDao dao;

	/**
	 * @return the dao
	 */
	public BasicDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(BasicDao dao) {
		this.dao = dao;
	}

	@Override
	public void createOrder(Order order) {
		dao.insert(order);
	}

	@Override
	public void modifyOrder(Order order) {
		dao.insertOrUpdate(order);
	}

	@Override
	public void deleteOrder(Order order) {
		dao.delete(order);
	}

	@Override
	public Order checkOrder(Long id) {
		return dao.load(id, new Order());
	}

	@Override
	public List<Order> listOfOrders() {
		return dao.loadAll(new Order());
	}

}
