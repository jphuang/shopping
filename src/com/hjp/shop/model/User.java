package com.hjp.shop.model;

import java.awt.List;
import java.sql.Timestamp;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class User extends Model<User> {

	public static User dao = new User();
	
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
		return this.password;
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
	
	public java.util.List<User> getAllUser() {
		return this.find("select * from tbl_user");
	}


	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
