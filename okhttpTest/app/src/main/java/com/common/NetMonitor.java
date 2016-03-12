package com.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by xuhuajian on 2016/3/11.
 */
public class NetMonitor {
    private NetworkInfo networkInfo;
    private static NetMonitor netMonitor;

    public static NetMonitor getInstance(){
       if(null == netMonitor){
           synchronized (NetMonitor.class){
               if(null == netMonitor) {
                   netMonitor = new NetMonitor();
               }
           }
        }
        return netMonitor;
    }

    public void init(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        setNetworkInfo(networkInfo);
    }

    public synchronized void setNetworkInfo(NetworkInfo Info){
        networkInfo = Info;
    }

    public synchronized boolean isConnected(){
        if(null != networkInfo) {
            return networkInfo.isConnected();
        }
        return false;
    }

    public synchronized String networkType(){
        if(null != networkInfo){
            return networkInfo.getTypeName();
        }
        return "no";
    }
}
