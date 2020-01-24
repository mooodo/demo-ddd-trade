/* 
 * Created by 2018年9月9日
 */
package com.demo2.trade.entity;

import java.util.Date;
import java.util.List;

import com.demo2.support.entity.Entity;

/**
 * The customer entity
 * @author fangang
 */
public class Customer extends Entity<Long> {
	private static final long serialVersionUID = 5704896146658318508L;
	private Long id;
	private String name;
	private String sex;
	private Date birthday;
	private String identification;
	private String phone_number;
	private List<Address> addresses;
	public Customer() {
		super();
	}
	
	public Customer(Long id, String name, String sex, Date birthday,
			String identification, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.identification = identification;
		this.phone_number = phoneNumber;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the identification
	 */
	public String getIdentification() {
		return identification;
	}
	/**
	 * @param identification the identification to set
	 */
	public void setIdentification(String identification) {
		this.identification = identification;
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

	/**
	 * @return the addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
