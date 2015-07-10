package com.demo.dubbo.callback;

import java.util.HashMap;
import java.util.Map;

import com.demo.dubbo.callback.inter.Notify;


public class NotifyImpl implements Notify {
	
	public Map<String, String> ret = new HashMap<String, String>();
	public Map<String, Throwable> errors = new HashMap<String, Throwable>();

	@Override
	public void onthrow(Throwable ex, String arg) {
		errors.put(arg, ex);
	}

	@Override
	public void onreturn(String msg, String arg) {
		System.out.println("onreturn:" + msg);
		ret.put(arg, msg);		
	}
}
