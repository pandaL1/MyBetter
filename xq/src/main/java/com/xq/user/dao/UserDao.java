/**
 * 
 */
package com.xq.user.dao;

import com.xq.user.pojos.User;

/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年12月4日 下午4:07:59
 * @vison 1.0
 */
public interface UserDao {

	public User login(String username,String password);
	
	public User regist(User user);
}
