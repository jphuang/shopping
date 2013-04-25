
package config;

import com.hjp.shop.controller.AdminControlller;
import com.hjp.shop.controller.UserController;
import com.hjp.shop.model.User;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
public class ShopConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		//me.setViewType(ViewType.VELOCITY);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/user",UserController.class);
		me.add("/admin",AdminControlller.class);
		
	}

	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://localhost/shopping","root", "root"); 
	    me.add(cp); 
	    ActiveRecordPlugin arp = new ActiveRecordPlugin(cp); 
	    me.add(arp); 
	    arp.addMapping("tbl_user", User.class); 
		    
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configHandler(Handlers me) {
		
	}

}
