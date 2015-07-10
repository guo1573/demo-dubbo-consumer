package com.demo.dubbo.sticky;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.inter.ProductService;

public class StickyProviderTest {
	
    public static void main(String[] args) throws Exception {
        String config = StickyProviderTest.class.getPackage().getName().replace('.', '/') + "/sticky-consumer.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();
		final ProductService productService = (ProductService) context.getBean("productService");
		
		/**
		 * 测试粘滞连接
		 * 测试负载均衡模式 修改方法的参数sticky=true
		 * 粘滞连接: 和一致哈希区负载均衡的区别是，不考虑参数是否一致，始终访问同一台服务器，除非出现异常
		 */
		for (int i = 0; i < 5000; i++) {
			System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] 参数为：" + i + "; providerAddr:"
					+ productService.getProviderName(String.valueOf(i)));
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.in.read();
    }
    
}
