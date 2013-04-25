package com.hjp.shop.controller;

import java.util.Date;

import com.hjp.shop.model.User;
import com.jfinal.core.Controller;

public class UserController extends Controller {
	public void index() {
		
		this.setAttr("userPage", User.dao.getAlldate());
	}


	public void register() {
		if(this.getPara("reg").equals("ok"))
		{
			String username = this.getPara("username");
			String password = this.getPara("password");
			String phone = this.getPara("phone");
			String addr = this.getPara("addr");
			
			boolean isSave = new User().set("username",username).set("password", password).set("phone", phone)
				.set("addr", addr).set("rdate", new Date()).save();
			if (isSave) {
				this.setAttr("reg", "yes");
				redirect("/user/login");
			}
		}
		else {
			this.renderJsp("register.html");
		}
	}
	
	public void login() {
		
		render("login.html");
		//if (this.getPara("reg").equals("yes")) {
			//renderHtml("×¢²á³É¹¦£¬ÇëµÇÂ½");
		//}
		
	}
	
	public void delete() {
		User.dao.deleteById(getParaToInt());
		redirect("/user/index");
	}
	
}
