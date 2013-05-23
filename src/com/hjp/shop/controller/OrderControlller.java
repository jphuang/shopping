package com.hjp.shop.controller;

import java.util.Date;
import java.util.List;

import com.hjp.shop.model.Cart;
import com.hjp.shop.model.CartItem;
import com.hjp.shop.model.Salesitem;
import com.hjp.shop.model.Salesorder;
import com.hjp.shop.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;

public class OrderControlller extends Controller {

	public void index() {
		if(getPara("buy")!=null && getPara("buy").equals("ok")){
			Cart cart = (Cart)getSessionAttr("cart");
			setAttr("items", cart.getItems());
		}
		render("order.html");
	}
	//添加事务拦截器
	@Before (Tx.class)
	public void add(){
		String username = getPara("username");
		String phone =getPara("phone");
		String addr = getPara("addr");
		if(username!=null && phone!=null && addr!=null){
			addr = addr + "/" + username + "（收） " + "电话: " + phone;
			int id = ((User)getSessionAttr("user")).getInt("id");
			Salesorder order = new Salesorder();
			order.set("userid", id).set("addr", addr).set("odate",new Date()).set("status",0);
			boolean ook = order.save();
			int oid = Salesorder.dao.getLastId();
			Cart cart = (Cart)getSessionAttr("cart");
			List<CartItem> items = cart.getItems();
			boolean iok =true;
			for (CartItem i : items) {
				Salesitem si = new Salesitem();
				si.set("productid",i.getProductId());
				si.set("unitprice",i.getPrice());
				si.set("pcount",i.getCount());
				si.set("orderid",oid);
				iok = iok && si.save();
			}
			if(ook && iok){
				setAttr("info", "下订单成功！");
				setSessionAttr("cart", null);
			}else{
				setAttr("info", "下订单失败！");
			}
		}
		render("addOk.html");
	}
	
	public void myOrder(){
		int uid = 0;
		if(getPara() !=null){
			uid = getParaToInt();
		}
		if(uid > 0){
			List<Salesorder> orderlists = Salesorder.dao.getOrdersByUid(uid);
			setAttr("orderlists", orderlists);
		}
		render("myOrder.html");
	}
}
