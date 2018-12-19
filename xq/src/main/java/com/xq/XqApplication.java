package com.xq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("com.xq")
@EnableScheduling
public class XqApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(XqApplication.class, args);
	}
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(XqApplication.class);
    }

}
