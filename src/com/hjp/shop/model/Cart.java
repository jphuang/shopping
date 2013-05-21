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

	public void updateCount(int productId, int count) {
		for (CartItem i : items) {
			if(i.getProductId()==productId){
				i.setCount(count);
			}
		}
	}

	public void deleteItem(int productId) {
		CartItem item=null;
		for (CartItem i : items) {
			if(i.getProductId()==productId){
				item = i;
			}
		}
		if(item!=null){
			items.remove(item);
		}
	}
}
