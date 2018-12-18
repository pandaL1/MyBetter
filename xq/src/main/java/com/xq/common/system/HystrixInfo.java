/**
 * 
 */
package com.xq.common.system;



import com.xq.common.enums.HttpMethod;
import com.xq.common.enums.URLAddressEnum;

/**
 * @PMAPPBoot
 * @author bruce  -李小强
 * @date 2018年11月22日 下午5:31:08
 * @vison 1.0
 */
public class HystrixInfo {

	private URLAddressEnum addressEnum;
	private Object params;
	private String groupKey;
	private String poolKey;
	private String annotation;
	private HttpMethod httpMethod;
	
	public HystrixInfo(URLAddressEnum addressEnum, String groupKey, String poolKey) {
		this.addressEnum = addressEnum;
		this.groupKey = groupKey;
		this.poolKey = poolKey;
	}
	
	
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}


	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}


	public String getAnnotation() {
		return annotation;
	}


	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	public Object getParams() {
		return params;
	}


	public void setParams(Object params) {
		this.params = params;
	}


	public URLAddressEnum getAddressEnum() {
		return addressEnum;
	}
	public void setAddressEnum(URLAddressEnum addressEnum) {
		this.addressEnum = addressEnum;
	}
	public String getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}
	public String getPoolKey() {
		return poolKey;
	}
	public void setPoolKey(String poolKey) {
		this.poolKey = poolKey;
	}
	
	
}
