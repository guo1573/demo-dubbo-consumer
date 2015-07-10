package com.demo.dubbo.callback;

import junit.framework.Assert;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.inter.ProductService;

public class CallBackProviderTest {

	public static void main(String[] args) throws Exception {
		String config = CallBackProviderTest.class.getPackage().getName().replace('.', '/') + "/callback-consumer.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
		context.start();
		final ProductService productService = (ProductService) context.getBean("productService");

		
		NotifyImpl notify = (NotifyImpl) context.getBean("demoCallback");
		String requestParament = "call request";
		String result = productService.sayHello(requestParament);
		
		// 说明callback正常被调用
		for (int i = 0; i < 10; i++) {
			if (!notify.ret.containsKey(requestParament)) {
				Thread.sleep(200);
			} else {
				break;
			}
		}
		Assert.assertEquals(requestParament, notify.ret.get(requestParament));
	}
}
