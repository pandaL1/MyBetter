/**
 * 
 */
package com.xq.common.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @PMAPPBoot 外部请求地址管理
 * @author bruce  -李小强
 * @date 2018年8月8日 下午6:06:11
 * @vison 1.0
 */

public enum URLAddressEnum{
	
	SOLR_URL_QUESTION(URLAddressTypeEnum.serach,1001,"http://search.woshipm.com:7777/question/queryQuestion",false,new AtomicLong(0l)),
	SOLR_URL_WPPOST(URLAddressTypeEnum.serach,1002,"http://search.woshipm.com:7777/wppost/queryWpPosts",false,new AtomicLong(0l)),
	SOLR_URL_AUTHOR(URLAddressTypeEnum.serach,1003,"http://search.woshipm.com:7777/author/queryAuthor",false,new AtomicLong(0l)),
	
	WECHAT_TOKEN(URLAddressTypeEnum.wechat,3001,"https://api.weixin.qq.com/cgi-bin/token",false,new AtomicLong(0l)),
	WECHAT_GETTICKET(URLAddressTypeEnum.wechat,3002,"http://api.weixin.qq.com/cgi-bin/ticket/getticket",false,new AtomicLong(0l)),
	WECHAT_QRCONNECT(URLAddressTypeEnum.wechat,3003,"https://open.weixin.qq.com/connect/qrconnect",false,new AtomicLong(0l)),
	
	PMAPP_HOTARTICLES(URLAddressTypeEnum.articles,5001,"http://api.woshipm.com/baidu/article/hot",false,new AtomicLong(0l)),
	BAIDU(URLAddressTypeEnum.passPort,4001,"https://www.baidu.com/",false,new AtomicLong(0l)),
;
    private URLAddressTypeEnum type;//请求类型
    private int code;
    private String address;//请求地址
    private boolean isUse;//是否使用线程池链接
    private AtomicLong invokCount=new AtomicLong(0l);

	private URLAddressEnum(URLAddressTypeEnum type, int code, String address, boolean isUse, AtomicLong invokCount) {
		this.type = type;
		this.code = code;
		this.address = address;
		this.isUse = isUse;
		this.invokCount = invokCount;
	}
	
	public AtomicLong getInvokCount() {
		return invokCount;
	}

	public URLAddressTypeEnum getType() {
		return type;
	}

	public int getCode() {
		return code;
	}

	public String getAddress() {
		this.invokCount.getAndIncrement();
		return address;
	}
	/**
	 * 外部接口统计调用
	 * @return
	 */
	public String getAddressCount() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isUse() {
		return isUse;
	}
	
	/**
	 * 获取链接地址集合
	 * @return
	 */
    public static List<String> getUrlAddressList(){
    	List<String> list=new ArrayList<>();
    	URLAddressEnum[] address=URLAddressEnum.values();
    	for (URLAddressEnum urlAddressEnum : address) {
    		if(urlAddressEnum.isUse()){
    			list.add(urlAddressEnum.getAddress());
    		}    		
		}
    	return list;
    }
    

	
}
