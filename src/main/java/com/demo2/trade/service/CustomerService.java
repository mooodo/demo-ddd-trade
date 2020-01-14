/* 
 * Created by 2019年1月13日
 */
package com.demo2.trade.service;

import com.demo2.trade.entity.Customer;

/**
 * The service of customer
 * @author fangang
 */
public interface CustomerService {
	/**
	 * save a customer
	 * @param customer
	 */
	public void save(Customer customer);
	/**
	 * delete a customer
	 * @param id
	 */
	public void delete(long id);
	/**
	 * @param id
	 * @return the customer of the id.
	 */
	public Customer load(long id);
}
