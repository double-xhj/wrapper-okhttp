package com.dao;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by xuhuajian on 2016/3/12.
 */
public abstract class JsonResponseHandler extends CBHandler<BaseBean> {
    /**
     * for parse the response content to entity object
     * @param content: response content
     * @return
     * entity object parsed
     * @throws IOException
     */
    private Object onParse(final String content) throws IOException{
        if(null == this.mBean)
            return null;

        try{
            return JSON.parseObject(content, this.mBean);
        }catch(Exception e){
            throw new IOException(e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    BaseBean getResponseData(Response response) throws Exception {
        if (null != response && null != response.body()) {
            String responseBody = response.body().string();
                /* parse the data to entity object */
            Object entity = this.onParse(responseBody);
            if (null != entity) {
                return (BaseBean) entity;
            }
        }
        throw new Exception("the response body is null!");
    }
}

