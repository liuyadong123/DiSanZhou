package com.example.dong.disanzhou.net;

import android.support.v7.widget.RecyclerView;

import com.example.dong.disanzhou.api.Api;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private final Retrofit retrofit;

    private RetrofitUtils(){
        HttpLoggingInterceptor httpLoggingInterceptor =new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient =new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Api.USER_Api)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        }
        public static  RetrofitUtils retrofitUtils(){
           if (retrofitUtils==null){
               synchronized (RetrofitUtils.class){
                   if (retrofitUtils==null){
                       retrofitUtils=new RetrofitUtils();
                   }
               }
           }
           return  retrofitUtils;
        }
       public void dopsot(HashMap<String,String> param,String url, HashMap<String,String> params, final RetrofitCallback callback){
           RetrofitView retrofitView = retrofit.create(RetrofitView.class);
           retrofitView.DoPost(param,url, params).enqueue(new Callback<ResponseBody>() {
               @Override
               public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                   try {
                       if (response.code()==200){
                           String string = response.body().string();
                           callback.success(string);
                       }

                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }

               @Override
               public void onFailure(Call<ResponseBody> call, Throwable t) {
                  callback.Failure("请求失败");
               }
           });

       }
    public void doget(HashMap<String,String> param,String url, final RetrofitCallback callback){
        RetrofitView retrofitView = retrofit.create(RetrofitView.class);
        retrofitView.DoGet(param,url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code()==200){
                        String string = response.body().string();
                        callback.success(string);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.Failure("请求失败");
            }
        });

    }
    public void nickname(HashMap<String,String> param,String url, HashMap<String,String> params, final RetrofitCallback callback){
        RetrofitView retrofitView = retrofit.create(RetrofitView.class);
        retrofitView.NickName(param,url, params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code()==200){
                        String string = response.body().string();
                        callback.success(string);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.Failure("请求失败");
            }
        });

    }
}
