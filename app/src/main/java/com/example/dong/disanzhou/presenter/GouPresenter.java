package com.example.dong.disanzhou.presenter;

import com.example.dong.disanzhou.bean.GouBean;
import com.example.dong.disanzhou.bean.ShopBean;
import com.example.dong.disanzhou.bean.XiangBean;
import com.example.dong.disanzhou.contract.GouContrct;
import com.example.dong.disanzhou.model.GouModle;
import com.example.dong.disanzhou.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;

public class GouPresenter extends GouContrct.GouPresenter {
    private GouModle modle;
    private GouContrct.IView view;
    public GouPresenter(GouContrct.IView view){
        modle=new GouModle();
        this.view=view;
    }
    @Override
    public void gou(HashMap<String, String> param,HashMap<String, String> params) {
        modle.gouModle(param,params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                GouBean gouBean = new Gson().fromJson(result, GouBean.class);
                view.gouSuccess(gouBean);

            }

            @Override
            public void ReFailure(String msg) {
                view.Failure(msg);
            }
        });
    }

    @Override
    public void shop(HashMap<String, String> param,HashMap<String, String> params) {
        modle.ShopModle(param,params, new RequestCallback() {
            @Override
            public void ReSuccess(String result) {
                ShopBean shopBean = new Gson().fromJson(result, ShopBean.class);
                view.ShopSuccess(shopBean);

            }

            @Override
            public void ReFailure(String msg) {
                 view.Failure(msg);
            }
        });
    }

    @Override
    public void xiang(HashMap<String, String> param, String id) {
       modle.XiangModle(param, id, new RequestCallback() {
           @Override
           public void ReSuccess(String result) {
               XiangBean xiangBean = new Gson().fromJson(result, XiangBean.class);
               view.XiangSuccess(xiangBean);

           }

           @Override
           public void ReFailure(String msg) {
             view.Failure(msg);
           }
       });
    }
}
