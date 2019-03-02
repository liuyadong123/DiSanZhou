package com.example.dong.disanzhou.net;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitView {
    @POST
    @FormUrlEncoded
    Call<ResponseBody> DoPost(@HeaderMap HashMap<String,String> param, @Url String url, @FieldMap HashMap<String, String> params);

    @GET
    Call<ResponseBody> DoGet(@HeaderMap HashMap<String,String> param, @Url String url);

    @PUT
    Call<ResponseBody> NickName(@HeaderMap HashMap<String,String> param,@Url String url, @QueryMap HashMap<String,String> params);


}
