package com.portfolio.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductDataPrice {// 商品
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;

	private Double price;

	public ProductDataPrice() {
		super();
	}
	
	public ProductDataPrice(ProductPrice productPrice) {
		super();
		this.date = productPrice.getDate();
		this.price = productPrice.getPrice();
	}
	
	public ProductDataPrice(Date date, Double price) {
		super();
		this.date = date;
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}