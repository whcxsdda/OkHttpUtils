package com.example.okhttp_wh;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 原则：代码复用强，少写多余的代码+节省系统的资源(只有一个Client对象)
 *
 * */
//单例的类:构造私有化 自行实例化 提供公开的方法
public class okhttpUtils {

    OkHttpClient okHttpClient;
    private okhttpUtils(){
        Log.d("wh","创建一次");
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    private static okhttpUtils okhttp = new okhttpUtils();//饿汉 没有现成安全问题

    public static okhttpUtils newInstance(){
        return okhttp;
    }


    //TODO 1:方法 get请求
    public void doGet(String url, final MyCallBack myCallBack){
        Request request = new Request.Builder().get().url(url).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                myCallBack.error(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                myCallBack.success(response.body().string());
            }
        });
    }
    //TODO 2:方法 post请求
    public void doPost(String url, final MyCallBack myCallBack, String[] keyname, String[] value){
        FormBody.Builder builder = new FormBody.Builder();

        for (int i = 0;i<keyname.length;i++){
            builder.add(keyname[i],value[i]);
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().post(formBody).url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                myCallBack.error(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                myCallBack.success(response.body().string());
            }
        });
    }

    public void download(String url,String path,MyCallBack myCallBack){

    }


    interface MyCallBack{
        public void success(String str);
        public void error(String message);

    }
}
