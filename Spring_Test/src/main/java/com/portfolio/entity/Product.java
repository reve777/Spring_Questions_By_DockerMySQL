package com.portfolio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Product {// 商品

	@Id
	@Column
	private Integer id;// 商品 ID
	@Column
	private String name;// 商品名稱
	@Column
	private String shortName;// 商品短名稱
	@Column
	private Boolean dataGrouping;// 資料是否分組

	public Product() {
		super();
	}
	public Product(ProductData productData) {
		super();
		this.id = productData.getId();
		this.name = productData.getName();
		this.shortName = productData.getShortName();
		this.dataGrouping = productData.getDataGrouping();
	}
	
	public Product(Integer id, String name, String shortName, Boolean dataGrouping) {
		super();
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.dataGrouping = dataGrouping;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Boolean getDataGrouping() {
		return dataGrouping;
	}

	public void setDataGrouping(Boolean dataGrouping) {
		this.dataGrouping = dataGrouping;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", shortName=" + shortName + ", dataGrouping=" + dataGrouping
				+ "]";
	}
}