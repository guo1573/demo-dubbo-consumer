package com.demo.dubbo.context;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.demo.inter.ProductService;

public class ContextProviderTest {

	public static void main(String[] args) throws Exception {
		String config = ContextProviderTest.class.getPackage().getName().replace('.', '/') + "/context-consumer.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
		context.start();
		final ProductService productService = (ProductService) context.getBean("productService");

		System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + " providerAddr:"
				+ productService.getProviderName("str"));//远程调用
		
		// 本端是否为消费端，这里会返回true
		boolean isConsumerSide = RpcContext.getContext().isConsumerSide(); 
		System.out.println("RpcContext.getContext().isConsumerSide:"+isConsumerSide);
		
		// 获取最后一次调用的提供方IP地址
		String serverIP = RpcContext.getContext().getRemoteHost(); 
		System.out.println("RpcContext.getContext().getRemoteHost:"+serverIP);
		
		// 获取当前服务配置信息，所有配置信息都将转换为URL的参数
		String application = RpcContext.getContext().getUrl().getParameter("application"); 
		System.out.println("RpcContext.getContext().getUrl().getParameter:"+application);
		// ...
		
		productService.getProviderName("str"); 
		// 注意：每发起RPC调用，上下文状态会变化
		// ...
	}
}
