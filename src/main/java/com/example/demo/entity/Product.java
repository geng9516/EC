package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_products")
public class Product {

	private Integer id;
	private String productType;
	private String productIntro;
	private String status;
	private Double sales;
	private Double cost;
	private Integer stock;
	private Integer accessNumber=0;
	private Timestamp dateCreated;
	private Timestamp dateModified;
	private Order order;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "product_type")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Column(name = "product_intro")
	public String getProductIntro() {
		return productIntro;
	}

	public void setProductIntro(String productIntro) {
		this.productIntro = productIntro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Column(name = "access_number")
	public Integer getAccessNumber() {
		return accessNumber;
	}
	public void setAccessNumber(Integer accessNumber) {
		this.accessNumber = accessNumber;
	}

	@Column(name = "date_created")
	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "date_modified")
	public Timestamp getDateModified() {
		return dateModified;
	}

	public void setDateModified(Timestamp dateModified) {
		this.dateModified = dateModified;
	}

	@JoinColumn(name = "order_id")
	@ManyToOne(fetch = FetchType.LAZY)
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	public Product(Integer id, String productType, String productIntro, String status, Double sales, Double cost,
			Integer stock, Integer accessNumber, Timestamp dateCreated, Timestamp dateModified, Order order) {
		super();
		this.id = id;
		this.productType = productType;
		this.productIntro = productIntro;
		this.status = status;
		this.sales = sales;
		this.cost = cost;
		this.stock = stock;
		this.accessNumber = accessNumber;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.order = order;
	}

	public Product() {
		super();
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productType=" + productType + ", productIntro=" + productIntro + ", status="
				+ status + ", sales=" + sales + ", cost=" + cost + ", stock=" + stock + ", accessNumber=" + accessNumber
				+ ", dateCreated=" + dateCreated + ", dateModified=" + dateModified + "]";
	}

}
