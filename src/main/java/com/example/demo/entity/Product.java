package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_products")
public class Product {

	private Integer id;
	private String productType;
	private String photo;
	private String productIntro;
	private String status;
	private Double sales;
	private Double cost;
	private Integer stock;
	private Integer accessNumber = 0;
	private Timestamp dateCreated;
	private Timestamp dateModified;
	private Set<Order> order = new HashSet<>();

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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	@JoinTable(name="order_product",
			joinColumns={@JoinColumn(name="product_id", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="order_id", referencedColumnName="ID")})
	@ManyToMany
	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productType=" + productType + ", photo=" + photo + ", productIntro="
				+ productIntro + ", status=" + status + ", sales=" + sales + ", cost=" + cost + ", stock=" + stock
				+ ", accessNumber=" + accessNumber + ", dateCreated=" + dateCreated + ", dateModified=" + dateModified
				+ "]";
	}


}
