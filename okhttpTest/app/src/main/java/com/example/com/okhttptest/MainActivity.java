package com.example.com.okhttptest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.common.NetMonitor;
import com.dao.Http;
import com.dao.HttpReq;
import com.dao.StringResponseHandler;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NetMonitor.getInstance().init(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final EditText etUrl = (EditText)findViewById(R.id.et_url);
        tvResult = (TextView)findViewById(R.id.tv_result);

        getData("https://www.baidu.com");
        Button button = (Button)findViewById(R.id.bt_open);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etUrl.getText().toString();
                if(!"".equals(url)){
                    String type = NetMonitor.getInstance().networkType();
                //    tvResult.setText("ok " + type);
                    getData(url);
                }

            }
        });
    }

    private void getData(String url){
        Http.getInstance().get(HttpReq.TEST_URL, null, new StringResponseHandler() {
            @Override
            public void onSuccess(String obj) {
                tvResult.setText(obj);
            }

            @Override
            public void onFailure(int errCode, String eresp) {
                tvResult.setText(eresp);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
