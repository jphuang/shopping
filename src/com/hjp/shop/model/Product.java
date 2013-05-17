package com.hjp.shop.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Product extends Model<Product> {

	private static final long serialVersionUID = 1L;
	private static final int pageSize = 5;	
	public static Product dao = new Product();
	
	public  Page<Product> getAllProduct(int pageNo){
		return this.paginate(pageNo,Product.pageSize, "select * ", " from tbl_product order by id asc");
	}

	public List<Product> search(int categoryid,
												String keyword,
												int minNormalprice,
												int maxNormalprice,
												int minMemberprice,
												int maxMembelprice, 
												Date minpdate, 
												Date maxpdate) 
	{
		String sql = "select * from tbl_product where 1=1 ";
		if(categoryid>0){
			sql += " and categoryid = " + categoryid;
		}
		if(keyword != null && !keyword.equals("")){
			sql += " and name like '%" + keyword + "%' or descr like '%" + keyword + "%' ";
		}
		
		if(minNormalprice > 0){
			sql += " and normalprice > " + minNormalprice;
		}
		if(maxNormalprice > 0){
			sql += " and normalprice < " + maxNormalprice;
		}
		if(minMemberprice > 0){
			sql += " and memberprice > " + minMemberprice;
		}
		if(maxMembelprice > 0){
			sql += " and memberprice < " + maxMembelprice;
		}
		if(minpdate != null){
			sql += " and pdate > '" + new SimpleDateFormat("yyyy-MM-dd").format(minpdate) + "'";
		}
	if(maxpdate !=null){
		sql += " and pdate < '" + new SimpleDateFormat("yyyy-MM-dd").format(maxpdate) +"'";
		}
		System.out.println(sql);
		return find(sql);
	}

}
