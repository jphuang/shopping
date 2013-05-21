package com.hjp.shop.controller;

import com.hjp.shop.model.Cart;
import com.hjp.shop.model.CartItem;
import com.hjp.shop.model.Product;
import com.jfinal.core.Controller;

public class CartController extends Controller {
	public void index(){
		int productid = 0;
		int count = 0;
		if(getPara("productid") !=null){
			productid = getParaToInt("productid");
		}
		if(getPara("count")!=null){
			count = getParaToInt("count");
		}
		
		Cart cart = (Cart)getSessionAttr("cart");
		if(cart==null){
			cart = new Cart();
			setSessionAttr("cart", cart);
		}
		CartItem item = new CartItem();
		if(productid!=0){
			item.setProductId(productid);
			item.setProductName(item.getProductName());
			item.setPrice(Product.dao.findById(productid).getDouble("normalprice"));
			item.setCount(count);
			cart.add(item);
		}
		setAttr("items", cart.getItems());
		render("cart.html");
	}
	
	public void update(){
		int productId = 0;
		int count = 0  ;
		if(getPara("productId")!=null){
			productId = getParaToInt("productId");
		}
		if(getPara("count")!=null){
			count = getParaToInt("count");
		}
		if(productId !=0 && count !=0){
			Cart cart = (Cart)getSessionAttr("cart");
			cart.updateCount(productId,count);
			renderText("ok");
			return;
		}
		renderNull();
	}
	public void deleteItem(){
		int productId = 0;
		if(getPara("productId")!=null){
			productId = getParaToInt("productId");
		}
		if(productId !=0 ){
			Cart cart = (Cart)getSessionAttr("cart");
			cart.deleteItem(productId);
			renderText("ok");
			return;
		}
		renderNull();
	}
}
