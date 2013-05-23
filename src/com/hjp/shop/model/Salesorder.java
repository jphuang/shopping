package com.hjp.shop.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class Salesorder extends Model<Salesorder> {

	private static final long serialVersionUID = 1L;
	
	public static Salesorder dao = new Salesorder();
	
	/**
	 * 通过用户id取得某一个用户的所有订单
	 * @param uid 用户id
	 * @return 订单列表
	 * 
	 */
	public List<Salesorder> getOrdersByUid(int uid) {
		String sql = "select * from tbl_salesorder where userid = " +uid;
		return find(sql);
	}
	/**
	 * 取得最大的那个id，一般用来取得刚自动生成的那个id，这个涉及到事务问题
	 * @return int id 
	 */
	public int getLastId(){
		return findFirst("select id from tbl_salesorder order by id desc limit 1").getInt("id");
	}
}
