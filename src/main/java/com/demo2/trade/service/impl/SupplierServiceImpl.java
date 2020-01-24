/* 
 * Created by 2019年1月30日
 */
package com.demo2.trade.service.impl;

import java.util.List;

import com.demo2.support.dao.BasicDao;
import com.demo2.trade.entity.Supplier;
import com.demo2.trade.service.SupplierService;

/**
 * The implement of the supplier service.
 * @author fangang
 */
public class SupplierServiceImpl implements SupplierService {
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
	public Supplier loadSupplier(long id) {
		Supplier supplier = new Supplier();
		return dao.load(id, supplier);
	}
	@Override
	public List<Supplier> listOfSuppliers() {
		Supplier supplier = new Supplier();
		return dao.loadAll(supplier);
	}
	@Override
	public List<Supplier> loadSuppliers(List<Long> ids) {
		Supplier supplier = new Supplier();
		return dao.loadForList(ids, supplier);
	}
}
