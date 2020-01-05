/*
 * created by 2019年7月23日 下午3:46:50
 */
package com.demo.trade.entity;

import java.io.Serializable;

import com.demo.support.entity.Entity;

/**
 * @author fangang
 */
public class OrderDetail extends Entity {
	private static final long serialVersionUID = 7010469293079068192L;
	private Long id;
	private Long order_id;
	private Long product_id;
	private Double quantity;
	private Double price;
	private Double amount;
	private Product product;
	
	@Override
	public Serializable getId() {
		return this.id;
	}

	@Override
	public void setId(Serializable id) {
		this.id = (Long) id;
	}

	/**
	 * @return the order_id
	 */
	public Long getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	/**
	 * @return the product_id
	 */
	public Long getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	/**
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

}
