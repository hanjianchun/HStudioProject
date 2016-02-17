package com.hjc.hstudioproject.okhttp;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Han on 2016/1/15.
 * email:hanjianchun_happy@163.com
 */
public class OkhttpUtils {
    /**
     * 创建OkHttp对象
     */
    OkHttpClient okHttpClient;

    public OkhttpUtils(OkHttpClient okHttpClient) {
        if(null == okHttpClient){
            okHttpClient = new OkHttpClient();
        }
    }

    /**
     * 通过http获取数据
     * @return
     */
    public String okHttpGet(String url){
        final Request request = new Request.Builder().url(url).build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.v("okHttpUtils","faild");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.v("okHttpUtils",response.body().toString());
            }
        });

        return "";
    }
}
