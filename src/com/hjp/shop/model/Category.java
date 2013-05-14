package com.hjp.shop.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class Category extends Model<Category>{

	private static final long serialVersionUID = 1L;
	
	//dao数据承载对象
	public static Category dao = new Category();
	
	public List<Category> getCategories(){
		ArrayList<Category> lists= new ArrayList<Category>();
		this.getCategories(lists, 0);
		return lists;
	}
	public void getCategories(List<Category> lists,int pid){
		List<Category> clist = find("select * from tbl_category where pid =" + pid);
		for (Category c : clist) {
			lists.add(c);
			if(c.getInt("isleaf") == 0){
				getCategories(lists,c.getInt("id"));
			}
		}
	}
}
