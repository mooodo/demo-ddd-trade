/* 
 * Created by 2019年1月13日
 */
package com.demo2.trade.service.impl;

import com.demo2.support.dao.BasicDao;
import com.demo2.trade.entity.Address;
import com.demo2.trade.entity.Customer;
import com.demo2.trade.service.CustomerService;

/**
 * The implement of the customer service
 * @author fangang
 */
public class CustomerServiceImpl implements CustomerService {
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
	public void save(Customer customer) {
		dao.insertOrUpdate(customer);
	}
	@Override
	public void delete(long id) {
		Customer customer = dao.load(id, new Customer());
		dao.delete(customer);
	}
	@Override
	public Customer load(long id) {
		Customer customer = new Customer();
		customer.setId(id);
		return dao.load(id, customer);
	}
	@Override
	public Address loadAddress(long id) {
		Address address = new Address();
		address.setId(id);
		return dao.load(id, address);
	}
}
