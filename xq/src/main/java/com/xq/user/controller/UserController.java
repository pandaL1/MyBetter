/**
 * 
 */
package com.xq.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xq.user.dao.UserDao;
import com.xq.user.pojos.User;

/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年12月4日 下午4:04:41
 * @vison 1.0
 */
@Controller
@RequestMapping(value ="/user/")
public class UserController {

	@Resource
	private UserDao userDao;
	/**
	 * 登录
	 */
	@RequestMapping(value="login")
	public ModelAndView login(@RequestParam(defaultValue="",required=true) String username,
			@RequestParam(defaultValue="",required=true) String password){
		ModelAndView mv=new ModelAndView();
		try {
			User user=userDao.login(username, password);
			
			if(user==null){
				mv.setViewName("404");
			}else{
				mv.addObject("user", user);
				mv.setViewName("index");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 注册
	 */
	@RequestMapping(value="regist")
	public ModelAndView regist(User user){
		ModelAndView mv=new ModelAndView();
		try {
			User user1=userDao.regist(user);
			
			if(user==null){
				mv.setViewName("404");
			}else{
				mv.addObject("user", user1);
				mv.setViewName("index");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
}
