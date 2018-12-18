/**
 * 
 */
package com.xq.fiction.pojos;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import com.mongodb.BasicDBObject;

/**
 * @xq
 * @author bruce -李小强
 * @date 2018年11月14日 上午11:37:04
 * @vison 1.0
 */
@Document(collection = "t_fiction")
public class FictionInfo {

	private Integer _id;// 主键id
	private String type;// 类型
	private String image;//封面图
	private String title;// 标题
	private String _abstract;// 摘要
	private String author;// 作者姓名
	private Long wordsCount;// 字数统计
	private int sectionCount;// 章节
	private int readTime;// 预估阅读时长
	private String downLoadUrl;// 下载链接
	private Long createTime;// 创建时间
	private long readCount;//浏览量
	private long clickCount;//点击量
	

	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getReadCount() {
		return readCount;
	}

	public void setReadCount(long readCount) {
		this.readCount = readCount;
	}

	public long getClickCount() {
		return clickCount;
	}

	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String get_abstract() {
		return _abstract;
	}

	public void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getWordsCount() {
		return wordsCount;
	}

	public void setWordsCount(Long wordsCount) {
		this.wordsCount = wordsCount;
	}

	public int getSectionCount() {
		return sectionCount;
	}

	public void setSectionCount(int sectionCount) {
		this.sectionCount = sectionCount;
	}

	public int getReadTime() {
		return readTime;
	}

	public void setReadTime(int readTime) {
		this.readTime = readTime;
	}

	public String getDownLoadUrl() {
		return downLoadUrl;
	}

	public void setDownLoadUrl(String downLoadUrl) {
		this.downLoadUrl = downLoadUrl;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	
	@SuppressWarnings("static-access")
	public Query getQueryObject(){
		Query query=new Query();		
		if(this._id>0){			
			query.addCriteria(new Criteria().where("_id").is(this._id));
		}
		if(!StringUtils.isEmpty(this.type)){			
			query.addCriteria(new Criteria().where("type").is(this.type));
		}
		if(!StringUtils.isEmpty(this.title)){			
			query.addCriteria(new Criteria().where("title").regex(this.title));
		}
		if(!StringUtils.isEmpty(this._abstract)){			
			query.addCriteria(new Criteria().where("_abstract").regex(this._abstract));
		}
		if(!StringUtils.isEmpty(this.author)){			
			query.addCriteria(new Criteria().where("title").regex(this.author));
		}
		return query;
	}
}
