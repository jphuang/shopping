package com.hjp.shop.model;


import java.security.MessageDigest;

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
	
	public User getUserByName(String name){
		String sqlString = "select * from tbl_user where username = '" + name + "'";
		return User.dao.findFirst(sqlString);
	}
	public String getPasswordByusername(String username) {
		String sqlString = "select password from tbl_user where username = '" + username + "'";
		return User.dao.findFirst(sqlString).getStr("password");
	}
	
	/**
	 * 利用MD5进行加密 　　
	 * 
	 * @author hjp
	 * @param String
	 *            str 待加密的字符串
	 * @return 　String 加密后的字符串
	 */
	public  static String EncoderByMd5(String str) {
		// 确定计算方法
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(str.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] byteArray = md5.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}

		return md5StrBuff.toString();
	}
}
