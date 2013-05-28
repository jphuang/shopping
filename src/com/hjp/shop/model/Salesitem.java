package com.hjp.shop.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class Salesitem extends Model<Salesitem> {

		private static final long serialVersionUID = 1L;
		public static Salesitem dao = new Salesitem();
		
		public List<Salesitem> getAllItem(){
			return find("select productid,count(pcount) count from tbl_salesitem group by productid");
		}

		public List<Salesitem> getItemByOid(int oid) {
			String sql = "select * from tbl_salesitem where orderid = " + oid;
			System.out.println(sql);
			return find(sql);
		}
		
		public Product getProduct(int pid){
			return Product.dao.findById(pid);
		}
}
