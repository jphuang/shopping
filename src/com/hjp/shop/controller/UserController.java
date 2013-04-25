package com.hjp.shop.controller;

import java.nio.channels.SelectableChannel;
import java.util.Date;

import org.eclipse.jetty.jndi.java.javaNameParser;

import com.hjp.shop.model.User;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

public class UserController extends Controller {
	public void index() {
		//this.setAttr("userPage", User.dao.paginate(1, 10, "select * ", " from tbl_user"));
		
		java.util.List<User> lists = User.dao.find("select * from tbl_user");
		
		System.out.println(lists);
		
		System.out.println(lists.size());
		
		for (User user : lists) {
			System.out.println(user instanceof User);
			System.out.println(user);
			System.out.println(user.getPassword());
			
		}
		

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
	
}
