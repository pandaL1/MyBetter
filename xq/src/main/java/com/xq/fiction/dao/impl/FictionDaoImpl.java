/**
 * 
 */
package com.xq.fiction.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.xq.fiction.dao.FictionDao;
import com.xq.fiction.pojos.FictionInfo;
import com.xq.fiction.pojos.FictionQueryObject;

/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年11月14日 上午11:32:51
 * @vison 1.0
 */
@Repository
public class FictionDaoImpl implements FictionDao{

	 @Autowired
	private MongoTemplate mongoTemplate;

	
	@Override
	public List<FictionInfo> findFictionList(FictionInfo info,FictionQueryObject queryObject) {
		Query query=info.getQueryObject();
		query=queryObject.setSortObject(query);
		return mongoTemplate.find(query, FictionInfo.class);
	}

}
