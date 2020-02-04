/*
 * created by 2019年7月22日 下午3:20:12
 */
package com.demo2.trade.entity;

import java.util.Date;
import java.util.List;

import com.demo2.support.entity.Entity;

/**
 * @author fangang
 */
public class Order extends Entity<Long> {
	private static final long serialVersionUID = 3997691790735159004L;
	private Long id;
	private Long customer_id;
	private Long address_id;
	private Double amount;
	private Date order_time;
	private String flag;
	private Address address;
	private Customer customer;
	private List<OrderItem> orderItems;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = (Long)id;
	}
	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customer_id;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customer_id = customerId;
	}
	/**
	 * @return the addressId
	 */
	public Long getAddressId() {
		return address_id;
	}
	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(Long addressId) {
		this.address_id = addressId;
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
	 * @return the orderTime
	 */
	public Date getOrderTime() {
		return order_time;
	}
	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(Date orderTime) {
		this.order_time = orderTime;
	}
	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * @return the orderItems
	 */
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
}
