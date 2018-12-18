/**
 * 
 */
package com.xq.common.enums;

/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年12月18日 下午2:29:04
 * @vison 1.0
 */
public enum Enums {

	articles("文章",1001),
	news("新闻",1002),
	fiction("小说",1003),
	images("图片",1004),
	video("视频",1005),
	books("书籍",1006),
	goods("商品",1007);
	
	
	
	private String name;
	private int code;
	
	
	private Enums() {
	}
	private Enums(String name, int code) {
		this.name = name;
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	
	
}
