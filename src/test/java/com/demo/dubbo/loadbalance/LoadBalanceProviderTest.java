package com.demo.dubbo.loadbalance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.inter.ProductService;

public class LoadBalanceProviderTest {
	
    public static void main(String[] args) throws Exception {
        String config = LoadBalanceProviderTest.class.getPackage().getName().replace('.', '/') + "/loadbalance-consumer.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();
		final ProductService productService = (ProductService) context.getBean("productService");
		
		/**
		 * 测试负载均衡方式
		 * 测试负载均衡模式 修改方法的参数loadbalance：random\roundrobin\leastactive\consistenthash
		 * 测试结果：roundrobin平均分发到服务端、leastactive延迟最少的会多分发、consistenthash相同参数会分发相同服务端
		 */
		for (int i = 0; i < 5000; i++) {
			System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + i + " providerAddr:"
					+ productService.getProviderName("str"));
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
    
}
