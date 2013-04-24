package com.hjp.shop.controller;

import com.jfinal.core.Controller;

public class UserController extends Controller {
	public void index() {
		String usernameString =  this.getPara("username");
		String password = this.getPara("password");
		String phone = this.getPara("phone");
		String addr = this.getPara("addr");
		
		System.out.println(usernameString + password + phone +  addr);
		this.renderHtml("<h1>Hello,Welcome To MyShop</h1>");

	}

	public void register() {
		this.renderJsp("register.html");
	}
	
}
