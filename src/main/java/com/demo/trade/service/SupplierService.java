/* 
 * Created by 2019年1月30日
 */
package com.demo.trade.service;

import java.util.List;

import com.demo.trade.entity.Supplier;

/**
 * The service of suppliers.
 * @author fangang
 */
public interface SupplierService {
	/**
	 * @param id
	 * @return the supplier
	 */
	public Supplier loadSupplier(long id);
	/**
	 * @param ids
	 * @return
	 */
	public List<Supplier> loadSupplier(List<Long> ids);
	
	/**
	 * @return the list of supplier
	 */
	public List<Supplier> listOfSupplier();
}
