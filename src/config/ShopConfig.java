package config;

import com.hjp.shop.controller.AdminControlller;
import com.hjp.shop.controller.UserController;
import com.jfinal.config.*;
import com.jfinal.render.ViewType;
public class ShopConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setViewType(ViewType.VELOCITY);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/user",UserController.class);
		me.add("/admin",AdminControlller.class);
		
	}

	@Override
	public void configPlugin(Plugins me) {
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configHandler(Handlers me) {
		
	}

}
