package com.demo.dubbo.token;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.inter.BaseService;
import com.demo.inter.ProductService;

public class TokenProviderTest {

	public static void main(String[] args) throws Exception {
		String config = TokenProviderTest.class.getPackage().getName().replace('.', '/') + "/token-consumer.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
		context.start();
		final BaseService baseService = (BaseService) context.getBean("baseService");

		/**
		 * 测试token
		 */
		System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] "
				+ baseService.sayHello("say hello world"));
		System.in.read();
	}

}
