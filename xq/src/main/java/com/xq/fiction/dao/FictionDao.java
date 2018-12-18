/**
 * 
 */
package com.xq.fiction.dao;

import java.util.List;

import com.xq.fiction.pojos.FictionInfo;
import com.xq.fiction.pojos.FictionQueryObject;

/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年11月14日 上午11:32:31
 * @vison 1.0
 */
public interface FictionDao {

	public List<FictionInfo> findFictionList(FictionInfo info,FictionQueryObject queryObject);
}
