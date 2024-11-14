package com.portfolio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class ProductPrice {// 價格

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer productid;// 商品 ID
	@Column
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column
	private Double price;// 價格

	public ProductPrice() {
		super();
	}

	public ProductPrice(Integer productid, ProductDataPrice productDataPrice) {
		super();
		this.productid = productid;
		this.date = productDataPrice.getDate();
		this.price = productDataPrice.getPrice();
	}

	public ProductPrice(Integer productid, Date date, Double price) {
		super();
		this.productid = productid;
		this.date = date;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
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

	@Override
	public String toString() {
		return "ProductPrice [id=" + id + ", productid=" + productid + ", date=" + date + ", price=" + price + "]";
	}

	

	
}