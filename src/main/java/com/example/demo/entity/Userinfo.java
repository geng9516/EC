package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_userinfo")
public class Userinfo {
	private Integer id;
	private String photo;
	private String userName;
	private String userNickname;
	private String tel;
	private String email;
	private Character sex;
	private String status;
	private String address;
	private Date birth;
	private Timestamp dateCreated;
	private Timestamp dateModified;
	private Set<UserLogin> userLogin;
	private Set<Order> oeder;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	//orphanRemoval = true ＝＝＝ orderを個別に削除できるようにする
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	public Set<Order> getOeder() {
		return oeder;
	}

	public void setOeder(Set<Order> oeder) {
		this.oeder = oeder;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getUserName() {
		return userName;
	}

	@Column(name = "user_name")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	@Column(name = "user_nickname")
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	@Column(name = "statusbyuser")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
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

	@OneToMany(mappedBy = "userinfo", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	public Set<UserLogin> getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(Set<UserLogin> userLogin) {
		this.userLogin = userLogin;
	}

	public Userinfo(Integer id, String photo, String userName, String userNickname, String tel, String email,
			Character sex, String status, String address, Date birth, Timestamp dateCreated, Timestamp dateModified,
			Set<UserLogin> userLogin, Set<Order> oeder) {
		super();
		this.id = id;
		this.photo = photo;
		this.userName = userName;
		this.userNickname = userNickname;
		this.tel = tel;
		this.email = email;
		this.sex = sex;
		this.status = status;
		this.address = address;
		this.birth = birth;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.userLogin = userLogin;
		this.oeder = oeder;
	}

	public Userinfo() {
		super();
	}

	@Override
	public String toString() {
		return "Userinfo [id=" + id + ", photo=" + photo + ", userName=" + userName + ", userNickname=" + userNickname
				+ ", tel=" + tel + ", email=" + email + ", sex=" + sex + ", status=" + status + ", address=" + address
				+ ", birth=" + birth + ", dateCreated=" + dateCreated + ", dateModified=" + dateModified
				+ ", userLogin=" + userLogin + ", oeder=" + oeder + "]";
	}



}
