package com.hjp.shop.controller;

import java.util.Date;

import com.hjp.shop.Interceptor.AdminInterceptor;
import com.hjp.shop.model.User;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.core.Controller;
import com.mchange.v2.c3p0.impl.NewPooledConnection;

@Before(AdminInterceptor.class)
public class AdminControlller extends Controller {

	public void index() {
		render("index.html");
	}
	
	public void searchUser() {
		String username = getPara("username");
		setAttr("user", User.dao.getUserByName(username));
	}
	
	public void listUser() {
		int pageNo;
		if (getPara()!=null) {
			try {
				pageNo = getParaToInt();
				if (pageNo <1 ) {
					pageNo = 1;
				}
			} catch (Exception e) {
				pageNo = 1;
				e.printStackTrace();
			}
		}else {
			pageNo = 1;
		}
		long pageCount = User.dao.getPageCount();
		if(pageNo > pageCount){
			pageNo =(int)pageCount;
		}
		this.setAttr("userPage", User.dao.getAlldate(pageNo));
		setAttr("pageNo",pageNo);
		setAttr("pageCount",pageCount);
		render("listUser.html");
	}
	
	public void addUser() {
		String username = getPara("username");
		if (username != null) {
			String password = User.EncoderByMd5(username + new Date()).substring(0,6);
			User user = new User().set("username", username).set("password", User.EncoderByMd5(password)).set("rdate",new Date()).set("phone", "10086").set("addr", "ÇëÐÞ¸ÄÄãµÄµØÖ·");
			if(user.save()){
				user.set("password", password);
				setAttr("newuser",user);
			}
		}
		render("addUser.html");
	}
	
	@ClearInterceptor
	public void login() {
		if (getPara("login") == null || !getPara("login").equals("ok")) {
			render("login.html");
		} else {
			String usernameString = getPara("username");
			String passwordString = getPara("password");

			passwordString = User.EncoderByMd5(passwordString);

			if (passwordString.equals(User.dao
					.getPasswordByusername(usernameString)))
			{
				this.setSessionAttr("user", User.dao.getUserByName(usernameString));
				redirect("/admin");
			}
		}
	}

}
