package com.hjp.shop.controller;

import java.security.MessageDigest;
import java.util.Date;

import com.hjp.shop.model.User;
import com.jfinal.core.Controller;

public class UserController extends Controller {
	
	public void index() {
		renderHtml("<div class='span3 offset4'><h1>Hello,welcome you</h1></div>");
	}
	
	public void admin() {
		this.setAttr("userPage", User.dao.getAlldate());
	}
	
	public void register(){

		if (getPara("reg") != null && getPara("reg").equals("ok")) {
			String username = this.getPara("username");
			String password = this.getPara("password");
			String phone    = this.getPara("phone");
			String addr     = this.getPara("addr");

			
			password = EncoderByMd5(password);
			
			System.out.println(password);
			
			boolean isSave = new User().set("username", username)
					.set("password", password).set("phone", phone)
					.set("addr", addr).set("rdate", new Date()).save();
			if (isSave) {
				this.setAttr("reg", "yes");
				redirect("/user/login");
			}
		} else {
			this.renderJsp("register.html");
		}
	}

	public void login() {
		if (getPara("login") == null || !getPara("login").equals("ok")) {
			render("login.html");
		}else {
			String usernameString = getPara("username");
			String passwordString = getPara("password");
			
			passwordString = EncoderByMd5(passwordString);
			
			if (passwordString.equals(User.dao.getPasswordByusername(usernameString))) {
				redirect("/user/admin");
			}
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

	/**
	 * 利用MD5进行加密 　　
	 * @author hjp
	 * @param String str 待加密的字符串
	 * @return 　String 加密后的字符串
	 */
	public String EncoderByMd5(String str){
		// 确定计算方法
		MessageDigest md5 =null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(str.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		byte[] byteArray = md5.digest();  

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}

		return md5StrBuff.toString();
	}
}
