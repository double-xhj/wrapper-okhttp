package com.dao;

import okhttp3.FormBody;

/**
 * this class encapsulate okhttp request data parameters
 *
 * @author xhj
 */

public class HttpParams {

    private FormBody.Builder mBuilder;
    private StringBuilder mKeyValues;

    public HttpParams() {
        mBuilder = new FormBody.Builder();
        mKeyValues = new StringBuilder();
    }

    public FormBody.Builder add(String name, String value) {
        mBuilder.add(name, value);
        mKeyValues.append(name);
        mKeyValues.append(" ");
        mKeyValues.append(value);
        mKeyValues.append(" ");
        return mBuilder;
    }

    public FormBody.Builder put(String name, String value) {
        mBuilder.add(name, value);
        mKeyValues.append(name);
        mKeyValues.append(" ");
        mKeyValues.append(value);
        mKeyValues.append(" ");
        return mBuilder;
    }

    public FormBody build() {
        return mBuilder.build();
    }

    public String toString() {
        return mBuilder.toString();
    }

    /**
     * @function 获取传递的参数值
     * @return
     */
    public String getKeyValues() {
        return mKeyValues.toString();
    }
}
