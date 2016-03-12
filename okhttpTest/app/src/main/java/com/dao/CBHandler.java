package com.dao;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import okhttp3.Response;

public abstract class CBHandler<T> implements Handler.Callback {

	private static final String TAG = "CBHandler";
	/*message id defined for send message*/
	protected final static int SUCCESS_MSG = 1;
	protected final static int FAILED_MSG = 2;
	
	/*handler for activity thread to process*/
	private Handler handler = null;

	/*for parse the response to entity*/
	protected Class<?> mBean = null;

	public CBHandler() {
		if (Looper.myLooper() != null) {
			this.handler = new Handler(this);
		}
	}

	public void setBeanType(Class<?> bean){
		mBean = bean;
	}


	/**
	 * overwrite this method for handle succeed response
	 * @param obj: success response message
	 */
	public abstract void onSuccess(T obj);
	
	/**
	 * overwrite this method for handle failed response, 
	 * which response is not 200 OK or failed to access the server
	 * @param errCode 0:failed to access the server，
	 *                1:response data is wrong，
	 *                2:response code is not 200
	 * @param eresp: error response message
	 */
	public abstract void onFailure(int errCode, String eresp);



	@SuppressWarnings("unchecked")
	T  getResponseData(Response response) throws Exception{
		return null;
	}

	public void processSuccess(Response response){
		try {
			if (this.handler != null) {
			/*send message to the caller through handler*/
				T resp = getResponseData(response);
				if(null != resp) {
					Message msg = this.handler.obtainMessage(SUCCESS_MSG, resp);
					this.handler.sendMessage(msg);
				}else{
					processError(1, "get null response!");
				}
			}
		}catch (Exception e){
			Log.e(TAG, "process response exception!");
			processError(1, "process response exception!");
		}
	}
	
	
	public void processError(int errCode, String eresp){
		if(this.handler != null){
			/*send failed message when exception*/
			Message msg = this.handler.obtainMessage(FAILED_MSG, new Object[]{errCode, eresp});
			this.handler.sendMessage(msg);
		}
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean handleMessage(Message msg){
		try{
			switch(msg.what){
			case SUCCESS_MSG:
				this.onSuccess((T)msg.obj);
				break;
			case FAILED_MSG:
				Object[] response = (Object[])msg.obj;
				this.onFailure((Integer) response[0], (String) response[1]);
				break;
			default:
				break;
			}
		}catch(Exception e){
//			Log.e("CBHandle", e.getMessage());
		}
		return true;
	}
}
