package com.demo.dubbo.merge;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.inter.ProductService;

public class MergeProviderTest {
	
    public static void main(String[] args) throws Exception {
        String config = MergeProviderTest.class.getPackage().getName().replace('.', '/') + "/merge-consumer.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();
		final ProductService productService = (ProductService) context.getBean("productService");
		
		//测试分组聚合
		List<String> list = productService.mergeResult();
		for(String str:list){
			System.out.println(str);
		}
		System.in.read();
    }
}
