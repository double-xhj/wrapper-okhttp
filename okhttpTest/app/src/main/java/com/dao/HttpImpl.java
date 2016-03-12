package com.dao;

import okhttp3.OkHttpClient;
import okhttp3.Request;


public class HttpImpl extends Http{
	
	private final OkHttpClient mClient = new OkHttpClient();

	private String get(String url, HttpParams params, HttpCallback callback) {

		Request request = null;
		if (null == params) {
			request = new Request.Builder().url(url).build();
		} else {
			request = new Request.Builder().url(url).post(params.build()).build();
		}
		mClient.newCall(request).enqueue(callback);
		return url;
	}

	public  String get(String url, CBHandler<?> handler){
		HttpCallback callback = new HttpCallback(handler,null);
		return get(url,null,callback);
	}

	@Override
	public String get(HttpReq type, HttpParams params, CBHandler<?> handler) {
		 HttpCallback callback = new HttpCallback(handler, type.getEntityClass());
		
		 return get(type.getPath(), params, callback);
	}
	
	@Override
	public String get(String url, HttpParams params, CBHandler<?> handler) {
		
		HttpCallback callback = new HttpCallback(handler, null);
		
		return get(url, params, callback);
	}
	
	private String post(String url, HttpParams params, HttpCallback callback){
		
		 Request.Builder builder = new Request.Builder();
		// builder.header("resoursetype", GlobalVariable.RESOURSE_TYPE + "");

				 
		 Request request = null;
		 if(null == params) {
			 request = builder.url(url).build(); 
		 } else {
			 request = builder.url(url)
					 .post(params.build())
					 .build();
		 }
		 
		mClient.newCall(request).enqueue(callback);
		return url;
	}

	@Override
	public String post(HttpReq type, HttpParams params, CBHandler<?> handler) {
		
		HttpCallback callback = new HttpCallback(handler, type.getEntityClass());
		 	 
		 return post(type.getPath(), params, callback);
	}



	@Override
	public String post(String url, HttpParams params, CBHandler<String> handler) {

		HttpCallback callback = new HttpCallback(handler, null);

		return post(url, params, callback);
	}
}
