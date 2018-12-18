/**
 * 
 */
package com.xq.common.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xq.common.enums.Enums;
import com.xq.common.enums.HttpMethod;
import com.xq.common.enums.URLAddressEnum;
import com.xq.common.tools.NetworkRequestUtils;

import net.sf.json.JSONObject;

/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年12月18日 下午2:37:09
 * @vison 1.0
 */
@Component
public class CoreTask {

	@Resource
	MongoTemplate mongoTemplate;
	/**
	 * 获取文章
	 */
	@Scheduled(fixedDelay=60*60*24*1000l)
	public void getArticles(){
		try {
			List <NameValuePair> params=new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("local", "jlcx"));
			params.add(new BasicNameValuePair("type", "1"));
			JSONObject result=NetworkRequestUtils.httpConnect(URLAddressEnum.PMAPP_HOTARTICLES, params, HttpMethod.post);
			mongoTemplate.insert(result, Enums.articles.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
