/* 
 * Created by 2018年9月9日
 */
package com.demo2.trade.entity;

import com.demo2.support.entity.Entity;

/**
 * The product entity
 * @author fangang
 */
public class Product extends Entity<Long> {
	private static final long serialVersionUID = 7149822235159719740L;
	private Long id;
	private String name;
	private Double price;
	private String unit;
	private Long supplier_id;
	private String classify;
	private String image;
	private Double original_price;
	private String tip;
	private Supplier supplier;
	
	public Product() { super(); }
	public Product(Long id, String name, Double price, String unit, Long supplierId,
			String classify) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.supplier_id = supplierId;
		this.classify = classify;
	}
	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = (Long)id;
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
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the supplierId
	 */
	public Long getSupplierId() {
		return supplier_id;
	}
	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(Long supplierId) {
		this.supplier_id = supplierId;
	}
	/**
	 * @return the classify
	 */
	public String getClassify() {
		return classify;
	}
	/**
	 * @param classify the classify to set
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the original_price
	 */
	public Double getOriginalPrice() {
		return original_price;
	}
	/**
	 * @param original_price the original_price to set
	 */
	public void setOriginalPrice(Double originalPrice) {
		this.original_price = originalPrice;
	}
	/**
	 * @return the tip
	 */
	public String getTip() {
		return tip;
	}
	/**
	 * @param tip the tip to set
	 */
	public void setTip(String tip) {
		this.tip = tip;
	}
	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}
	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
}
