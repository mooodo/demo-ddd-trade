/* 
 * create by 2020年2月1日 下午5:25:13
 */
package com.demo2.support.entity;

import com.demo2.support.entity.Entity;

/**
 * @author fangang
 */
public class User extends Entity<Integer> {
	private static final long serialVersionUID = 1374542349924806607L;
	private int id;
	private String name;
	
	public User() {}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}


	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
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

}
