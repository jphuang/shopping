package com.hjp.shop.model;

import java.sql.Timestamp;

public class User {
	private String addr;
	private int id;
	private String password;
	private String phone;
	private Timestamp rdate;
	private String username;

	public String getAddr() {
		return addr;
	}

	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public Timestamp getRdate() {
		return rdate;
	}

	public String getUsername() {
		return username;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRdate(Timestamp rdate) {
		this.rdate = rdate;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
