package com.dao;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import android.util.Log;

import com.alibaba.fastjson.JSON;

public class HttpCallback implements Callback {

	/*handler for send message to relate activity*/
	private CBHandler<?> handler = null;

	public HttpCallback(CBHandler<?> handler, Class<?> entityClass){
		this.handler = handler;
		this.handler.setBeanType(entityClass);
	}
	
	
	@Override
	public void onFailure(Call arg0, IOException arg1) {
		this.handler.processError(0, arg1.getLocalizedMessage());	
	}

	@Override
	public void onResponse(Call arg0, Response arg1) throws IOException {
		if (arg1.isSuccessful()) {
			this.handler.processSuccess(arg1);
		} else {
			this.handler.processError(arg1.code(), arg1.message());
		}
	}
}
