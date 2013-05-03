package com.hjp.shop.controller;

import java.util.Date;

import com.hjp.shop.model.User;
import com.jfinal.core.Controller;

public class UserController extends Controller {

	public void index() {
		renderHtml("<div class='span3 offset4'><h1>Hello,welcome you</h1></div>");
	}



	public void register() {

		if (getPara("reg") != null && getPara("reg").equals("ok")) {
			String username = this.getPara("username");
			String password = this.getPara("password");
			String phone = this.getPara("phone");
			String addr = this.getPara("addr");

			password = User.EncoderByMd5(password);

			System.out.println(password);

			boolean isSave = new User().set("username", username)
					.set("password", password).set("phone", phone)
					.set("addr", addr).set("rdate", new Date()).save();
			if (isSave) {
				this.setAttr("reg", "yes");
				redirect("/");
			}
		} else {
			this.renderJsp("register.html");
		}
	}

	

	public void delete() {
		User.dao.deleteById(getParaToInt());
		redirect("/user/admin");
	}

	public void verify() {
		String username = getPara("name");
		renderText(User.dao.verify(username));
	}

	
}
