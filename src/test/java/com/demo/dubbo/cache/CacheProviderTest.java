package com.demo.dubbo.cache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.inter.ProductService;

public class CacheProviderTest {

	public static void main(String[] args) throws Exception {
		String config = CacheProviderTest.class.getPackage().getName().replace('.', '/') + "/cache-consumer.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
		context.start();
		final ProductService productService = (ProductService) context.getBean("productService");

		/**
		 * 调用方法参数一致时，服务调用被缓存了，不再需要服务端执行
		 */
		for (int i = 0; i < 10; i++) {
			System.out.println(i+"：服务端执行时间[" + new SimpleDateFormat("HH:mm:ss").format(productService.getProviderCurrentDate("1"))
					+ "] ");
			Thread.sleep(1000);
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println(i+"：服务端执行时间[" + new SimpleDateFormat("HH:mm:ss").format(productService.getProviderCurrentDate("2"))
					+ "] ");
			Thread.sleep(1000);
		}
	}

}
