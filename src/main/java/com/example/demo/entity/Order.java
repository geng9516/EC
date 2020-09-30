package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_order")
public class Order {
	@Id
	private Integer orderid;
	private Integer pid;
	private String username;
	private String statusbyorder;
	private String statusbybuy;
	private Double total;
	private Double payment;
	private String address;
	private Integer numberbyorder;
	private Timestamp date_pay;
	private Timestamp date_out;
	private Timestamp date_arrival;
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatusbyorder() {
		return statusbyorder;
	}
	public void setStatusbyorder(String statusbyorder) {
		this.statusbyorder = statusbyorder;
	}
	public String getStatusbybuy() {
		return statusbybuy;
	}
	public void setStatusbybuy(String statusbybuy) {
		this.statusbybuy = statusbybuy;
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
	public Integer getNumberbyorder() {
		return numberbyorder;
	}
	public void setNumberbyorder(Integer numberbyorder) {
		this.numberbyorder = numberbyorder;
	}
	public Timestamp getDate_pay() {
		return date_pay;
	}
	public void setDate_pay(Timestamp date_pay) {
		this.date_pay = date_pay;
	}
	public Timestamp getDate_out() {
		return date_out;
	}
	public void setDate_out(Timestamp date_out) {
		this.date_out = date_out;
	}
	public Timestamp getDate_arrival() {
		return date_arrival;
	}
	public void setDate_arrival(Timestamp date_arrival) {
		this.date_arrival = date_arrival;
	}
	public Order(Integer orderid, Integer pid, String username, String statusbyorder, String statusbybuy, Double total,
			Double payment, String address, Integer numberbyorder, Timestamp date_pay, Timestamp date_out,
			Timestamp date_arrival) {
		super();
		this.orderid = orderid;
		this.pid = pid;
		this.username = username;
		this.statusbyorder = statusbyorder;
		this.statusbybuy = statusbybuy;
		this.total = total;
		this.payment = payment;
		this.address = address;
		this.numberbyorder = numberbyorder;
		this.date_pay = date_pay;
		this.date_out = date_out;
		this.date_arrival = date_arrival;
	}
	public Order() {
		super();
	}



}
