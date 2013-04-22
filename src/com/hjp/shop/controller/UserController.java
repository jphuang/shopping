package com.hjp.shop.controller;

import com.jfinal.core.Controller;
public class UserController extends Controller{
	public void index(){
		this.renderHtml("<h1>Hello,Welcome To MyShop</h1>");
		
	}
	public void register(){
		this.renderJsp("/user/register.jsp");
	}
}
