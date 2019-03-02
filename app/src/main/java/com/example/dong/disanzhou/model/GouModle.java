package com.example.dong.disanzhou.model;

import com.example.dong.disanzhou.api.Api;
import com.example.dong.disanzhou.contract.GouContrct;
import com.example.dong.disanzhou.contract.GouContrct.IModle;
import com.example.dong.disanzhou.net.RequestCallback;
import com.example.dong.disanzhou.net.RetrofitCallback;
import com.example.dong.disanzhou.net.RetrofitUtils;
import com.example.dong.disanzhou.net.RetrofitView;

import java.util.HashMap;

public class GouModle implements IModle {
    @Override
    public void gouModle(HashMap<String, String> param,HashMap<String, String> params, final RequestCallback callback) {
        RetrofitUtils.retrofitUtils().doget(param,Api.Gou_Api, new RetrofitCallback() {
            @Override
            public void success(String result) {
                callback.ReSuccess(result);
            }

            @Override
            public void Failure(String msg) {
               callback.ReFailure(msg);
            }
        });
    }

    @Override
    public void ShopModle(HashMap<String, String> param,HashMap<String, String> params, final RequestCallback callback) {
        String keyword = params.get("keyword");
        String page = params.get("page");
        String count = params.get("count");
        RetrofitUtils.retrofitUtils().doget(param, Api.Shop_User + "?keyword=" + keyword + "&page=" + page + "&count=" + count, new RetrofitCallback() {
            @Override
            public void success(String result) {
                callback.ReSuccess(result);
            }

            @Override
            public void Failure(String msg) {
             callback.ReFailure(msg);
            }
        });
    }

    @Override
    public void XiangModle(HashMap<String, String> param, String id, final RequestCallback callback) {
        RetrofitUtils.retrofitUtils().doget(param, Api.Xing_User + "?commodityId=" + id, new RetrofitCallback() {
            @Override
            public void success(String result) {
                callback.ReSuccess(result);
            }

            @Override
            public void Failure(String msg) {
             callback.ReFailure(msg);
            }
        }) ;

    }
}
