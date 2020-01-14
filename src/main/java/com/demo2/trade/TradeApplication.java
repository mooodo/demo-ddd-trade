/* 
 * Created by 2018年9月9日
 */
package com.demo2.trade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * The application for trade.
 * @author fangang
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages={"com.demo2"})
@ImportResource(locations={"classpath:applicationContext-*.xml"})
@MapperScan("com.demo2.support.dao")
public class TradeApplication {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TradeApplication.class, args);
		System.out.println(".....................................");
		System.out.println("....The Trade Application started....");
		System.out.println(".....................................");
	}
}
