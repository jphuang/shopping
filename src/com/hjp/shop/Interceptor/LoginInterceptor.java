package com.hjp.shop.Interceptor;

import com.hjp.shop.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class LoginInterceptor implements Interceptor {

	public void intercept(ActionInvocation ai) {
		User user = (User) ai.getController().getSessionAttr("user");

		if (user != null) {
			if (User.dao.verify(user.getStr("username")).equals("no")) {
				ai.getController().setAttr("user", user);
			}
		}
		ai.invoke();
	}
}
