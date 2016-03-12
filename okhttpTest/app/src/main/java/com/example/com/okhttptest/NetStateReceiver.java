package com.example.com.okhttptest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.common.NetMonitor;

public class NetStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        NetMonitor.getInstance().setNetworkInfo(networkInfo);
        if(null != networkInfo && networkInfo.isConnected()){
            Toast.makeText(context, networkInfo.getTypeName(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "not connect network", Toast.LENGTH_SHORT).show();
        }

    }

}
