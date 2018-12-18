/**
 * 
 */
package com.xq.fiction.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xq.fiction.pojos.FictionInfo;
import com.xq.fiction.pojos.FictionQueryObject;
import com.xq.fiction.service.impl.FictionServiceImpl;

/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年11月14日 上午11:31:52
 * @vison 1.0
 */
@Controller
@RequestMapping("/fiction")
public class FictionController {

	@Resource(name="fictionServiceImpl")
	private FictionServiceImpl fictionServiceImpl;
	/**
	 * 查询小说列表
	 * @param fictionInfo
	 * @return
	 */
	@RequestMapping("/queryFictionList")
	public ModelAndView queryFictionList(FictionInfo info,FictionQueryObject queryObject){
		ModelAndView mv=new ModelAndView();
		try {
			mv.addObject("list", fictionServiceImpl.findFictionList(info, queryObject));
			mv.setViewName("/views/fiction/fictionList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
}
