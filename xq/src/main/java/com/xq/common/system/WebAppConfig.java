/**
 * 
 */
package com.xq.common.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @xq
 * @author bruce  -李小强
 * @date 2018年11月14日 下午4:10:00
 * @vison 1.0
 */

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	 @Override
     public void addViewControllers(ViewControllerRegistry registry) {
             registry.addViewController("/").setViewName("login");
             registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
     }
    
    
}
