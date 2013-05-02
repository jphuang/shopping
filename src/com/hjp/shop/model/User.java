package com.hjp.shop.model;


import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class User extends Model<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3025124637286140521L;
	public static User dao = new User();
	
	public Page<User> getAlldate() {
		return this.paginate(1, 10, "select * ", " from tbl_user order by id asc");
	}
	
	public String verify(String username) {
		String sqlString = "select * from tbl_user where username='" + username + "'";
		return User.dao.findFirst(sqlString)==null ? "yes" : "no";
	}
	
	public String getPasswordByusername(String username) {
		String sqlString = "select password from tbl_user where username = '" + username + "'";
		return User.dao.findFirst(sqlString).getStr("password");
	}
}
