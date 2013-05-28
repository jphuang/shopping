package com.hjp.shop.model;


import java.security.MessageDigest;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class User extends Model<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3025124637286140521L;
	
	private static final int pageSize = 5;	
	
	public static User dao = new User();
	
	/**
	 * 取得数据库中的某一页数据
	 * @param pageNo 第几页
	 * @return  pageSize 条记录
	 */
	public Page<User> getAlldate(int pageNo) {
		
		return this.paginate(pageNo,User.pageSize, "select * ", " from tbl_user order by id asc");
	}
	
	/**
	 *检查数据库中有没有这个用户名
	 * @param username String  要检查的用户名
	 * @return String 没有返回yes，有返回no
	 */
	public String verify(String username) {
		String sqlString = "select * from tbl_user where username='" + username + "'";
		return User.dao.findFirst(sqlString)==null ? "yes" : "no";
	}
	/**
	 * 根据用户名取得数据库User的一条记录
	 * @param name String 用户名
	 * @return  User 
	 */
	public User getUserByName(String name){
		String sqlString = "select * from tbl_user where username = '" + name + "'";
		return User.dao.findFirst(sqlString);
	}
	/**
	 * 根据用户名取得相对应的用户密码
	 * @param username 用户名
	 * @return String password
	 */
	public String getPasswordByusername(String username) {
		String sqlString = "select password from tbl_user where username = '" + username + "'";
		return User.dao.findFirst(sqlString).getStr("password");
	}
	
	/**
	 * 利用MD5进行加密 　　
	 * @author hjp
	 * @param String str 待加密的字符串
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

	public long getPageCount() {
		long count = findFirst("select count(*) count from tbl_user").getLong("count");
		return (count + User.pageSize -1)/User.pageSize;
	}
}
