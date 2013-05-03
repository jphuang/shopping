package com.hjp.shop.controller;

import com.hjp.shop.Interceptor.AdminInterceptor;
import com.hjp.shop.model.User;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.core.Controller;

@Before(AdminInterceptor.class)
public class AdminControlller extends Controller {

	public void index() {

	}
	
	public void searchUser() {
		String username = getPara("username");
		setAttr("user", User.dao.getUserByName(username));
	}
	
	public void listUser() {
		this.setAttr("userPage", User.dao.getAlldate());
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
