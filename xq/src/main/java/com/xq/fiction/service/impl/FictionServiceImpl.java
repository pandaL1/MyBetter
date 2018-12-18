/**
 * 
 */
package com.xq.fiction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.fiction.dao.FictionDao;
import com.xq.fiction.pojos.FictionInfo;
import com.xq.fiction.pojos.FictionQueryObject;
import com.xq.fiction.service.FictionService;

/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年11月14日 上午11:33:31
 * @vison 1.0
 */
@Service("fictionServiceImpl")
public class FictionServiceImpl implements FictionService{

	@Autowired
	FictionDao fictionDao;
	@Override
	public List<FictionInfo> findFictionList(FictionInfo info, FictionQueryObject queryObject) {

		return fictionDao.findFictionList(info, queryObject);
	}

}
