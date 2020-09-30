package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer product_id;
	private String product_type;
	private String product_intro;
	private Double sales;
	private Double cost;
	private Integer stock;
	private Integer Access_number;
	private Timestamp date_created;
	private Timestamp date_modified;
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getProduct_intro() {
		return product_intro;
	}
	public void setProduct_intro(String product_intro) {
		this.product_intro = product_intro;
	}
	public Double getSales() {
		return sales;
	}
	public void setSales(Double sales) {
		this.sales = sales;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getAccess_number() {
		return Access_number;
	}
	public void setAccess_number(Integer access_number) {
		Access_number = access_number;
	}
	public Timestamp getDate_created() {
		return date_created;
	}
	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}
	public Timestamp getDate_modified() {
		return date_modified;
	}
	public void setDate_modified(Timestamp date_modified) {
		this.date_modified = date_modified;
	}
	public Product(Integer product_id, String product_type, String product_intro, Double sales, Double cost,
			Integer stock, Integer access_number, Timestamp date_created, Timestamp date_modified) {
		super();
		this.product_id = product_id;
		this.product_type = product_type;
		this.product_intro = product_intro;
		this.sales = sales;
		this.cost = cost;
		this.stock = stock;
		Access_number = access_number;
		this.date_created = date_created;
		this.date_modified = date_modified;
	}
	public Product() {
		super();
	}



}
