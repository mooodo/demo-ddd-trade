/* 
 * Created by 2019年1月30日
 */
package com.demo.trade.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.demo.support.dao.BasicDao;
import com.demo.trade.entity.Supplier;
import com.demo.trade.service.SupplierService;

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
	public List<Supplier> listOfSupplier() {
		Supplier supplier = new Supplier();
		return dao.loadAll(supplier);
	}
	@Override
	public List<Supplier> loadSupplier(List<Long> ids) {
		Supplier supplier = new Supplier();
		List<Serializable> list = new ArrayList<>();
		for(Long id : ids) list.add(id);
		return dao.loadForList(list, supplier);
	}
}
