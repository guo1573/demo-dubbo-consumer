package com.demo.dubbo.actives;

import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.demo.inter.ProductService;

public class ActivesProviderTest {
	
    public static void main(String[] args) throws Exception {
        String config = ActivesProviderTest.class.getPackage().getName().replace('.', '/') + "/actives-consumer.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();
		final ProductService productService = (ProductService) context.getBean("productService");
		
		/**
		 * 测试并发数 场所可以开启几个客户端
		 * 修改服务端provider.xml：<dubbo:protocol>的参数executes
		 */
		testExecutes(productService);

		System.in.read();
    }
    
	/**
	 * 
	 * @desc 异步调用测试
	 *
	 */
	public static void testExecutes(ProductService productService) {
		try {
			// 此时sayHello的请求同时在执行，客户端不需要启动多线程来支持并行，而是借助NIO的非阻塞完成。
			productService.sayHello("async call request1",500);
			Future<String> str1Future = RpcContext.getContext().getFuture(); 
			productService.sayHello("async call request2",500);
			Future<String> str2Future = RpcContext.getContext().getFuture(); 
			productService.sayHello("async call request3",500);
			Future<String> str3Future = RpcContext.getContext().getFuture(); 
			productService.sayHello("async call request4",500);
			Future<String> str4Future = RpcContext.getContext().getFuture(); 
			productService.sayHello("async call request5",500);
			Future<String> str5Future = RpcContext.getContext().getFuture(); 
//			productService.sayHello("async call request6",500);
//			Future<String> str6Future = RpcContext.getContext().getFuture(); 

			 // 如果str1已返回，直接拿到返回值，否则线程wait住，等待str返回后，线程会被notify唤醒。
			System.out.println(str1Future.get());
			System.out.println(str2Future.get()); 
			System.out.println(str3Future.get()); 
			System.out.println(str4Future.get()); 
			System.out.println(str5Future.get()); 
//			System.out.println(str6Future.get()); 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
