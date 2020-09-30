package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
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

}
