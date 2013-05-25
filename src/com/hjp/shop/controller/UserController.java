package com.hjp.shop.controller;

import java.util.Date;

import com.hjp.shop.Interceptor.UserInterceptor;
import com.hjp.shop.model.User;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.core.Controller;

@Before(UserInterceptor.class)
public class UserController extends Controller {

	@ClearInterceptor
	public void index() {
		renderHtml("<div class='span3 offset4'><h1>Hello,welcome you</h1></div>");
	}

	@ClearInterceptor
	public void register() {

		if (getPara("reg") != null && getPara("reg").equals("ok")) {
			String username = this.getPara("username");
			String password = this.getPara("password");
			String phone = this.getPara("phone");
			String addr = this.getPara("addr");
			password = User.EncoderByMd5(password);
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

	public void selfServer() {
		User user = (User) getSessionAttr("user");
		setAttr("user", user);
		render("selfServer.html");
	}

	@ClearInterceptor
	public void verify() {
		String username = getPara("name");
		renderText(User.dao.verify(username));
	}

	@ClearInterceptor
	public void login() {
		if (getPara("login") != null && getPara("login").equals("ok")) {
			String usernameString = getPara("username");
			String passwordString = getPara("password");
			passwordString = User.EncoderByMd5(passwordString);
			if (passwordString.equals(User.dao.getPasswordByusername(usernameString))) {
				this.setSessionAttr("user",User.dao.getUserByName(usernameString));
				String path = (String)getSessionAttr("path");
				if(path!=null){
					
					redirect(path);
					return;
				}{
					redirect("/");
				}
				return;
			} 
		}
		String path = getRequest().getHeader("referer");
		setSessionAttr("path", path);
		render("index.html");
	}

	public void userinfo() {
		String info = this.getPara(0);
		User user = (User) getSessionAttr("user");
		setAttr("user", user);
		if (info.equals("info"))
			render("/user/userinfo.html");
		else if (info.equals("update"))
			render("/user/userUpdate.html");
		else if (info.equals("updatePassword"))
			render("/user/updatePassword.html");
	}

	public void update() {
		User user = (User) getSessionAttr("user");
		String phone = getPara("phone");
		String addr = getPara("addr");
		if (phone == null || addr == null) {
			renderHtml("<span class='red'>电话和地址都不能为空<span>");
		} else {
			user.set("phone", phone);
			user.set("addr", addr);
			if (!user.update()) {
				renderHtml("<span class='red'>修改失败<span>");
			} else {
				renderHtml("<span class='green'>修改成功<span>");
			}
		}
	}

	public void updatePassword() {
		User user = (User) getSessionAttr("user");
		String password = getPara("password");
		String password1 = getPara("password1");
		String password2 = getPara("password2");

		if (password == null || !User.EncoderByMd5(password).equals(user.get("password"))) {
			renderHtml("<span class='red'>旧密码不正确<span>");
			return;
		}
		if (password1 != null && password1.equals(password2)) {
			user.set("password", User.EncoderByMd5(password1));
			if (user.update()) {
				renderHtml("<span class='green'>密码修改成功<span>");
			} else {
				renderHtml("<span class='red'>密码修改成功<span>");
			}
		} else {
			renderHtml("<span class='red'>两个密码不一样<span>");
		}
	}
	public void logout(){
		setSessionAttr("user", null);
		redirect("/");
	}
}
