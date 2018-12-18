/**
 * 
 */
package com.xq.user.pojos;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年12月4日 下午4:19:41
 * @vison 1.0
 */
@Document(collection = "t_user")
public class User {

	private String userName;
	
	private String password;
	
	private int sex;
	
	private String mobile;
	
	private String email;
	
	private String headImg;
	
	private String hobby;
	
	private long lastLoginTime;

	private boolean enabled;
	
	
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	
}
