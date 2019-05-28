package com.example.okhttp_wh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get请求
        okhttpUtils.newInstance()
                .doGet("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=10&page=1",
                        new okhttpUtils.MyCallBack() {
                    @Override
                    public void success(String str) {
                        Log.d("wh","get:"+str);
                    }

                    @Override
                    public void error(String message) {
                        Log.d("wh","error:"+message);
                    }
                });

        //post请求
        okhttpUtils.newInstance().doPost("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=10", new okhttpUtils.MyCallBack() {
            @Override
            public void success(String str) {
                Log.d("wh","post:"+str);
            }

            @Override
            public void error(String message) {
                Log.d("wh","error:"+message);
            }
        },new String[]{"page"},new String[]{"1"});
    }
}
