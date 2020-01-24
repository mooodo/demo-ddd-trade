/* 
 * Created by 2018年9月10日
 */
package com.demo2.trade.service.impl;

import java.util.List;

import com.demo2.support.dao.BasicDao;
import com.demo2.trade.entity.Product;
import com.demo2.trade.service.ProductService;

/**
 * The implement of the product service.
 * @author fangang
 */
public class ProductServiceImpl implements ProductService {
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
	public void saveProduct(Product product) {
		dao.insertOrUpdate(product);
	}

	@Override
	public void saveProductList(List<Product> listOfProducts) {
		dao.insertOrUpdateForList(listOfProducts);
	}

	@Override
	public void deleteProduct(Long id) {
		Product product = new Product();
		dao.delete(id, product);
	}

	@Override
	public void deleteProductList(List<Long> ids) {
		List<Product> listOfProducts = dao.loadForList(ids, new Product());
		dao.deleteForList(listOfProducts);
	}

	@Override
	public Product getProduct(Long id) {
		Product template = new Product();
		return dao.load(id, template);
	}

	@Override
	public List<Product> getProductList(List<Long> ids) {
		return dao.loadForList(ids, new Product());
	}
}
