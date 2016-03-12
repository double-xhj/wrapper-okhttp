package com.dao;


public enum HttpReq {
	

	//结算页相关接口
	TEST_URL("https://www.baidu.com",null);
	
			
	
	private String path = null;
	private Class<?> entityClass = null;
	
	HttpReq(String path,  Class<?> entityClass){
		this.path = path;
		this.entityClass = entityClass;
	}

	public String getPath() {
		return this.path;
	}
	

	public Class<?> getEntityClass() {
		return this.entityClass;
	}
};
