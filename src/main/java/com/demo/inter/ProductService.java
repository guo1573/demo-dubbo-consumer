package com.demo.inter;


import java.util.Date;
import java.util.List;

import com.demo.entity.Product;


public interface ProductService {
	
	public String sayHello(final String str);
	
	public String sayHello(final String str,Integer waitTime);
	
	public Product getProduct(final Product pros);
	
	public String getProviderName(String str);
	
	Date getProviderCurrentDate(String str);
	
	List<String> mergeResult();
}
