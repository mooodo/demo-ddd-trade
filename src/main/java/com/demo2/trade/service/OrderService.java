/*
 * created by 2019年7月23日 下午4:18:12
 */
package com.demo2.trade.service;

import java.util.List;

import com.demo2.trade.entity.Order;

/**
 * @author fangang
 */
public interface OrderService {
	/**
	 * @param order
	 */
	public void createOrder(Order order);
	/**
	 * @param order
	 */
	public void modifyOrder(Order order);
	/**
	 * @param order
	 */
	public void deleteOrder(Order order);
	/**
	 * @param id
	 * @return
	 */
	public Order checkOrder(Long id);
	/**
	 * @return
	 */
	public List<Order> listOfOrders();
}
