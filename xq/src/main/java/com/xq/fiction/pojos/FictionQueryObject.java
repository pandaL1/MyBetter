/**
 * 
 */
package com.xq.fiction.pojos;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年11月14日 下午2:55:48
 * @vison 1.0
 */
public class FictionQueryObject {

	private int readCountSort=-1;
	
	private int clickCountSort=-1;
	
	private int createTimeSort=-1;
	
	private int wordsCountSort=-1;

	private int pageNo=1;
	
	private int pageSize=20;
	
	
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getReadCountSort() {
		return readCountSort;
	}

	public void setReadCountSort(int readCountSort) {
		this.readCountSort = readCountSort;
	}

	public int getClickCountSort() {
		return clickCountSort;
	}

	public void setClickCountSort(int clickCountSort) {
		this.clickCountSort = clickCountSort;
	}

	public int getCreateTimeSort() {
		return createTimeSort;
	}

	public void setCreateTimeSort(int createTimeSort) {
		this.createTimeSort = createTimeSort;
	}

	public int getWordsCountSort() {
		return wordsCountSort;
	}

	public void setWordsCountSort(int wordsCountSort) {
		this.wordsCountSort = wordsCountSort;
	}
	
	public Query setSortObject(Query query){		
		if(this.readCountSort==0){
			query.with(new Sort(Direction.ASC, "readCount"));
		}else if(this.readCountSort==1){
			query.with(new Sort(Direction.DESC, "readCount"));
		}
		if(this.clickCountSort==0){
			query.with(new Sort(Direction.ASC, "clickCount"));
		}else if(this.clickCountSort==1){
			query.with(new Sort(Direction.DESC, "clickCount"));
		}
		if(this.createTimeSort==0){
			query.with(new Sort(Direction.ASC, "createTime"));
		}else if(this.createTimeSort==1){
			query.with(new Sort(Direction.DESC, "createTime"));
		}
		if(this.wordsCountSort==0){
			query.with(new Sort(Direction.ASC, "wordsCount"));
		}else if(this.wordsCountSort==1){
			query.with(new Sort(Direction.DESC, "wordsCount"));
		}
		query.skip((pageNo-1)*pageSize);
		query.limit(pageSize);
		return query;
	}
	
}
