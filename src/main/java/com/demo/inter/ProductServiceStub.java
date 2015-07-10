package com.demo.inter;

import java.util.Date;
import java.util.List;

import com.demo.entity.Product;
import com.demo.inter.ProductService;

public class ProductServiceStub implements ProductService {

	final ProductService productService;

	public ProductServiceStub(final ProductService productService) {
		this.productService = productService;
	}

	@Override
	public String sayHello(String str) {
		System.out.println("execute stub");
		// 此代码在客户端执行
		// 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
		
		try {
			return productService.sayHello(str);
		} catch (Exception e) {
			// 你可以容错，可以做任何AOP拦截事项
			return "容错数据";
		}

	}

	@Override
	public String sayHello(String str, Integer waitTime) {
		System.out.println("execute stub");
		// 此代码在客户端执行
		// 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
		
		try {
			return productService.sayHello(str, waitTime);
		} catch (Exception e) {
			// 你可以容错，可以做任何AOP拦截事项
			return "容错数据";
		}
	}

	@Override
	public Product getProduct(Product pros) {
		System.out.println("stub return");
		return productService.getProduct(pros);
	}

	@Override
	public String getProviderName(String str) {
		System.out.println("stub return");
		return productService.getProviderName(str);
	}

	@Override
	public Date getProviderCurrentDate(String str) {
		System.out.println("stub return");
		return productService.getProviderCurrentDate(str);
	}

	@Override
	public List<String> mergeResult() {
		System.out.println("stub return");
		return productService.mergeResult();
	}

}
