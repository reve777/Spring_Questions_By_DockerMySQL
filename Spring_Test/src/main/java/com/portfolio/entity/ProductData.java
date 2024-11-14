package com.portfolio.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class ProductData {// 商品

	private Integer id;// 商品 ID

	private String name;// 商品名稱

	private String shortName;// 商品短名稱

	private Boolean dataGrouping;// 資料是否分組

	private List<ProductDataPrice> datas;

	public ProductData() {
		super();
	}

	public ProductData(Product product, List<ProductDataPrice> productDataPrice) {
		super();
		this.id = product.getId();
		this.name = product.getName();
		this.shortName = product.getShortName();
		this.dataGrouping = product.getDataGrouping();
		this.datas = productDataPrice;
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

	public List<ProductDataPrice> getDatas() {
		return datas;
	}

	public void setDatas(List<ProductDataPrice> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "ProductData [id=" + id + ", name=" + name + ", shortName=" + shortName + ", dataGrouping="
				+ dataGrouping + ", datas=" + datas + "]";
	}
	
	

}