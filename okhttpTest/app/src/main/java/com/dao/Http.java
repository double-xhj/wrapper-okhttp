package com.dao;

/**
 * Usage example
 * 
<pre>
CBHttpParams body = new CBHttpParams();
...
body.add("session_id", GlobalVariable.SESSION_ID);
...
CBHttp.getInstance().get(CBHttpReq.PRODUCT_INFO, body, new CBHandler<Object>(){
</pre>
 * 
 * @author xhj
 */
public abstract class Http {

	private static Http instance = null;
	
	/**return a single instance */
	public static Http getInstance() {
		if (instance == null) {
			synchronized (Http.class) {
				if (null == instance) {
					instance = new HttpImpl();
				}
			}
		}
		return instance;
	}
	
	/**
	 * request data by the {@code type} with {@code parameters}, the {@code handler} will be invoked
	 * when the request finished or failed.
	 * @param type: type of the request
	 * @param params: parameters for the request
	 * @param handler: callback handler after request finished
	 *  //@throws Exception
	 */
	public abstract String get(HttpReq type, HttpParams params, CBHandler<?> handler);
	
	/**
	 * request data by the {@code url} with {@code parameters}, the {@code handler} will be invoked
	 * when the request finished or failed.
	 * @param url: url of the request
	 * @param params: parameters for the request
	 * @param handler: callback handler after request finished
	 * //@throws Exception
	 */
	public abstract String get(String url, HttpParams params, CBHandler<?> handler);

	public abstract String get(String url, CBHandler<?> handler);

	
	/**
	 * request data by the {@code type} with {@code parameters}, the {@code handler} will be invoked
	 * when the request finished or failed.
	 * @param type: type of the request
	 * @param params: parameters for the request
	 * @param handler: callback handler after request finished
	 * //@throws Exception
	 */
	public abstract String post(HttpReq type, HttpParams params, CBHandler<?> handler);
	

	/**
	 * request data by the {@code url} with {@code parameters}, the {@code handler} will be invoked
	 * when the request finished or failed.
	 * @param url: url of the request
	 * @param params: parameters for the request
	 * @param handler: callback handler after request finished
	 * //@throws Exception
	 */
	public abstract String post(String url, HttpParams params, CBHandler<String> handler);
	
}
