package com.hjp.shop.Interceptor;

import java.util.Date;

import com.hjp.shop.model.Category;
import com.hjp.shop.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class LoginInterceptor implements Interceptor {
	public void intercept(ActionInvocation ai) {
		User user = (User) ai.getController().getSessionAttr("user");

		if (user != null) {
			if (User.dao.verify(user.getStr("username")).equals("no")) {
				ai.getController().setAttr("user", user);
				Record log = new Record().set("username",user.get("username")).set("controller",ai.getControllerKey()).set("act", ai.getActionKey()).set("logdate",new Date());
				Db.save("tbl_log",log);
			}
		}
		ai.getController().setAttr("categortList" ,Category.dao.getAllFirstLevelCategory());
		ai.invoke();
	}
}
