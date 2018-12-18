/**
 * 
 */
/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年12月4日 下午4:08:08
 * @vison 1.0
 */
package com.xq.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.xq.user.dao.UserDao;
import com.xq.user.pojos.User;
@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	private MongoTemplate mongoTemplate;

	
	@SuppressWarnings("static-access")
	@Override
	public User login(String username, String password) {
		Query query=new Query();
		query.addCriteria(new Criteria().where("username").is(username));
		query.addCriteria(new Criteria().where("password").is(password));
		
		Update update=new Update();
		update.update("lastLoginTime", System.currentTimeMillis());
		User user= mongoTemplate.findAndModify(query, update, User.class);
		
		return user;
	}



	@Override
	public User regist(User user) {
		mongoTemplate.insert(user);
		return user;
	}
}