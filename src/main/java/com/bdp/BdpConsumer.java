package com.bdp;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ysc.bdp.address.service.outward.IAddressService;

public class BdpConsumer {
	
	IAddressService addressService;
	
	public IAddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(IAddressService addressService) {
		this.addressService = addressService;
	}

	public void start()  {
		
		long startTime=System.currentTimeMillis(); 
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			try {
				addressService.getAllProvinceList();
				System.out.println("["
						+ new SimpleDateFormat("HH:mm:ss").format(new Date())
						+ "] " + i);
				//Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		long endTime=System.currentTimeMillis(); //获取结束时间
		System.out.println("运行5000次时间： "+(endTime-startTime)+"ms");
	}
	
}
