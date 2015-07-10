package com.demo.dubbo.stub;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.inter.ProductService;

public class StubProviderTest {

	public static void main(String[] args) throws Exception {
		String config = StubProviderTest.class.getPackage().getName().replace('.', '/') + "/stub-consumer.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
		context.start();
		
		final ProductService productService = (ProductService) context.getBean("productService");

		for (int i = 0; i < 10; i++) {
			System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] "
					+ productService.sayHello("call request1"));
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] "
				+ productService.sayHello("call request1",10000));
		System.in.read();
	}

}
