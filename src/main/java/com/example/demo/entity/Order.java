package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_order")
public class Order {

	private Integer id;
	private String orderId;
	private String statusByOrder;
	private String statusByBuy;
	//支払方法
	private String buyWay;
	private Double total;
	private Double payment;
	private String address;
	private Integer numberByOrder;
	private Timestamp dateCreated;
	private Timestamp dateModified;
	//支払時間
	private Timestamp payTime;
	//確認時間
	private Timestamp ConfirmationTime;
	private Userinfo user;
	private Set<Product> product = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuyWay() {
		return buyWay;
	}

	public void setBuyWay(String buyWay) {
		this.buyWay = buyWay;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "statusbyorder")
	public String getStatusByOrder() {
		return statusByOrder;
	}

	public void setStatusByOrder(String statusByOrder) {
		this.statusByOrder = statusByOrder;
	}

	@Column(name = "statusbybuy")
	public String getStatusByBuy() {
		return statusByBuy;
	}

	public void setStatusByBuy(String statusByBuy) {
		this.statusByBuy = statusByBuy;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "numberbyorder")
	public Integer getNumberByOrder() {
		return numberByOrder;
	}

	public void setNumberByOrder(Integer numberByOrder) {
		this.numberByOrder = numberByOrder;
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

	@Column(name = "pay_time")
	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	@Column(name = "confirmation_time")
	public Timestamp getConfirmationTime() {
		return ConfirmationTime;
	}

	public void setConfirmationTime(Timestamp confirmationTime) {
		ConfirmationTime = confirmationTime;
	}

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	public Userinfo getUser() {
		return user;
	}

	public void setUser(Userinfo user) {
		this.user = user;
	}

	@ManyToMany(mappedBy="order")
	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderId=" + orderId + ", statusByOrder=" + statusByOrder + ", statusByBuy="
				+ statusByBuy + ", buyWay=" + buyWay + ", total=" + total + ", payment=" + payment + ", address="
				+ address + ", numberByOrder=" + numberByOrder + ", dateCreated=" + dateCreated + ", dateModified="
				+ dateModified + ", payTime=" + payTime + ", ConfirmationTime=" + ConfirmationTime + "]";
	}

}
