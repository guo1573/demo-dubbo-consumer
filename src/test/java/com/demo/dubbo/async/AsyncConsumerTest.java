package com.demo.dubbo.async;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.demo.entity.Product;
import com.demo.inter.ProductService;

public class AsyncConsumerTest {

	public static void main(String[] args) throws Exception {

		String config = AsyncConsumerTest.class.getPackage().getName().replace('.', '/') + "/async-consumer.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
		context.start();

		final ProductService productService = (ProductService) context.getBean("productService");
		// 有返回值
		Future<String> f = RpcContext.getContext().asyncCall(new Callable<String>() {
			public String call() throws Exception {
				return productService.sayHello("async call request");
			}
		});
		System.out.println("async call ret :" + f.get());

		// 无返回值
		RpcContext.getContext().asyncCall(new Runnable() {
			public void run() {
				productService.sayHello("oneway call request1");
				productService.sayHello("oneway call request2");
			}
		});
		
		testAsync(productService);
		System.in.read();
	}

	/**
	 * 
	 * @desc 异步调用测试
	 *
	 */
	public static void testAsync(ProductService productService) {
		try {
			productService.sayHello("async call request3");// 此调用会立即返回null
			Future<String> str1Future = RpcContext.getContext().getFuture(); // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。
			productService.sayHello("async call request4");
			Future<String> str2Future = RpcContext.getContext().getFuture(); // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。

			// 此时sayHello的请求同时在执行，客户端不需要启动多线程来支持并行，而是借助NIO的非阻塞完成。
			System.out.println(str1Future.get()); // 如果str1已返回，直接拿到返回值，否则线程wait住，等待product返回后，线程会被notify唤醒。
			System.out.println(str2Future.get()); // 如果str2已返回，直接拿到返回值，否则线程wait住，等待str返回后，线程会被notify唤醒。

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
