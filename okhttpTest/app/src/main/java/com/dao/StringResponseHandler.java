package com.dao;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by xuhuajian on 2016/3/12.
 */
public abstract class StringResponseHandler extends CBHandler<String> {

    @Override
    String getResponseData(Response response) throws Exception {
        if (null != response && null != response.body()) {
            return response.body().string();
        }

        throw new Exception("the response body is null!");
    }
}
