package com.hjp.shop.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Product extends Model<Product> {

	private static final long serialVersionUID = 1L;
	private static final int pageSize = 5;	
	public static Product dao = new Product();
	
	public  Page<Product> getAllProduct(int pageNo){
		return this.paginate(pageNo,Product.pageSize, "select * ", " from tbl_product order by id asc");
	}

}
