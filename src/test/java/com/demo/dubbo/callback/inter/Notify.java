package com.demo.dubbo.callback.inter;

public interface Notify {
	
	void onreturn(String msg, String id);

	void onthrow(Throwable ex, String id);
}
