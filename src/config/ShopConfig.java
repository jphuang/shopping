
package config;

import com.hjp.shop.Interceptor.LoginInterceptor;
import com.hjp.shop.controller.AdminControlller;
import com.hjp.shop.controller.CartController;
import com.hjp.shop.controller.CategoryController;
import com.hjp.shop.controller.IndexController;
import com.hjp.shop.controller.OrderControlller;
import com.hjp.shop.controller.ProductController;
import com.hjp.shop.controller.ReportController;
import com.hjp.shop.controller.UserController;
import com.hjp.shop.model.Category;
import com.hjp.shop.model.Product;
import com.hjp.shop.model.Salesitem;
import com.hjp.shop.model.Salesorder;
import com.hjp.shop.model.User;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
public class ShopConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/user",UserController.class);
		me.add("/admin",AdminControlller.class);
		me.add("/",IndexController.class);
		me.add("/category",CategoryController.class);
		me.add("/product",ProductController.class);
		me.add("/cart",CartController.class);
		me.add("/order",OrderControlller.class);
		me.add("/report",ReportController.class);
		
	}

	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://localhost/shopping","root", "root"); 
	    me.add(cp); 
	    ActiveRecordPlugin arp = new ActiveRecordPlugin(cp); 
	    me.add(arp); 
	    arp.addMapping("tbl_user", User.class); 
	    arp.addMapping("tbl_category", Category.class); 
	    arp.addMapping("tbl_product", Product.class); 
	    arp.addMapping("tbl_salesitem", Salesitem.class); 
	    arp.addMapping("tbl_salesorder", Salesorder.class); 
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new LoginInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		
	}

}
