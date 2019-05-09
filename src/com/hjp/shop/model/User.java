package com.hjp.shop.model;


import java.security.MessageDigest;
import java.util.Objects;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class User extends Model<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3025124637286140521L;
	
	private static final int PAGE_SIZE = 5;
	
	public static User dao = new User();
	
	/**
	 * 取得数据库中的某一页数据
	 * @param pageNo 第几页
	 * @return  PAGE_SIZE 条记录
	 */
	public Page<User> getAllDate(int pageNo) {
		return this.paginate(pageNo,User.PAGE_SIZE, "select * ", " from tbl_user order by id asc");
	}
	
	/**
	 *检查数据库中有没有这个用户名
	 * @param username String  要检查的用户名
	 * @return String 没有返回yes，有返回no
	 */
	public String verify(String username) {
		String sqlString = "select * from tbl_user where username= ? ";
		return User.dao.findFirst(sqlString,username)==null ? "yes" : "no";
	}
	/**
	 * 根据用户名取得数据库User的一条记录
	 * @param name String 用户名
	 * @return  User 
	 */
	public User getUserByName(String name){
		String sqlString = "select * from tbl_user where username = ?";
		return User.dao.findFirst(sqlString,name);
	}
	/**
	 * 根据用户名取得相对应的用户密码
	 * @param username 用户名
	 * @return String password
	 */
	public String getPasswordByusername(String username) {
		String sqlString = "select password from tbl_user where username = ?";
		User first = User.dao.findFirst(sqlString, username);
		if(Objects.isNull(first)){
			return null;
		}
		return first.getStr("password");
	}
	
	/**
	 * 利用MD5进行加密 　　
	 * @author hjp
	 * @param  str 待加密的字符串
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
		assert md5 != null;
		byte[] byteArray = md5.digest();
		StringBuilder md5StrBuff = new StringBuilder();
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
		long count = findFirst("select count(1) count from tbl_user").getLong("count");
		return (count + User.PAGE_SIZE -1)/User.PAGE_SIZE;
	}
}
