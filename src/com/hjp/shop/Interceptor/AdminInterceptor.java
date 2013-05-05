package com.hjp.shop.Interceptor;

import com.hjp.shop.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class AdminInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		User user = (User) ai.getController().getSessionAttr("user");
		if (user != null && user.get("username").equals("admin")) {
			ai.invoke();
		} else{
			ai.getController().redirect("/admin/login");
		}
	
	}

}
