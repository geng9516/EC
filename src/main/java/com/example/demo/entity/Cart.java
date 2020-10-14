package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_cart")
public class Cart {

	private Integer id;
	private Integer pId;
	private Integer uId;
	private String productType;
	private String productIntro;
	private Double sales;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductIntro() {
		return productIntro;
	}

	public void setProductIntro(String productIntro) {
		this.productIntro = productIntro;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

	public Cart(Integer id, Integer pId, Integer uId, String productType, String productIntro, Double sales) {
		super();
		this.id = id;
		this.pId = pId;
		this.uId = uId;
		this.productType = productType;
		this.productIntro = productIntro;
		this.sales = sales;
	}

	public Cart() {
		super();
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", pId=" + pId + ", uId=" + uId + ", productType=" + productType + ", productIntro="
				+ productIntro + ", sales=" + sales + "]";
	}

}
