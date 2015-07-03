package com.demo.dubbo.consumer;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.demo.inter.ProductService;

public class HessianConsumer {

	public static void main(String[] args) {
		String serviceUrl = "http://10.20.100.29:8888/service/product";
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			ProductService productService = (ProductService) factory.create(ProductService.class, serviceUrl);
			String result = productService.testStringTransfer("test");
			System.out.println(result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
