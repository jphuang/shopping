package com.hjp.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	List<CartItem> items = new ArrayList<CartItem>();

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	public void add(CartItem item){
		for (CartItem i : items) {
			if(i.getProductId()==item.getProductId()){
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		items.add(item);
	}
}
