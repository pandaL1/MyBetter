/**
 * 
 */
package com.xq.common.enums;

/**
 * @PMAPPBoot 外部请求地址类型管理
 * @author bruce  -李小强
 * @date 2018年8月8日 下午6:06:11
 * @vison 1.0
 */

public enum URLAddressTypeEnum{
	serach("search","搜索用"),
	vipCourse("course","课程"),
	passPort("passPort","账号中心"),
	wechat("wechat","微信服务号"),
	ads("ads","广告服务"),
	articles("articles","文章");
    
    private String name;
    private String des;
    
    
	private URLAddressTypeEnum(String name, String des) {
		this.name = name;
		this.des = des;
	}
	public String getName() {
		return name;
	}
	public String getDes() {
		return des;
	}

    
    
	
    

	
}
