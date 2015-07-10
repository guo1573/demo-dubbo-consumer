package com.demo.dubbo.mock;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.inter.ProductService;

public class MockProviderTest {

	public static void main(String[] args) throws Exception {
		String config = MockProviderTest.class.getPackage().getName().replace('.', '/') + "/mock-consumer.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
		context.start();
		final ProductService productService = (ProductService) context.getBean("productService");

		for (int i = 0; i < 10; i++) {
			System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] "
					+ productService.sayHello("call request1",10000));
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.in.read();
	}
}
