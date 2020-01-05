/*
 * created by 2019年7月22日 下午3:24:47
 */
package com.demo.trade.entity;

import java.io.Serializable;

import com.demo.support.entity.Entity;

/**
 * @author fangang
 */
public class Address extends Entity {
	private static final long serialVersionUID = -7715747957884378980L;
	private Long id;
	private Long customer_id;
	private String country;
	private String province;
	private String city;
	private String zone;
	private String address;
	private String phone_number;
	/**
	 * @return the id
	 */
	public Serializable getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Serializable id) {
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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}
	/**
	 * @param zone the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phone_number;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phone_number = phoneNumber;
	}
}
