package com.example.dong.disanzhou.contract;

import com.example.dong.disanzhou.bean.GouBean;
import com.example.dong.disanzhou.bean.ShopBean;
import com.example.dong.disanzhou.bean.XiangBean;
import com.example.dong.disanzhou.net.RequestCallback;

import java.util.HashMap;

public interface GouContrct {
    abstract class GouPresenter{
        public abstract  void gou(HashMap<String,String> param,HashMap<String,String> params);

        public  abstract  void shop(HashMap<String,String> param,HashMap<String,String> params);
        public  abstract  void xiang(HashMap<String,String> param,String id);
    }
    interface  IModle{
        void gouModle(HashMap<String,String> param,HashMap<String,String> params, RequestCallback callback);
        void ShopModle(HashMap<String,String> param,HashMap<String, String> params, RequestCallback callback);
        void XiangModle(HashMap<String,String> param,String id, RequestCallback callback);

    }
    interface  IView{
        void gouSuccess(GouBean gouBean);
        void Failure(String msg);
        void ShopSuccess(ShopBean shopBean);
        void XiangSuccess(XiangBean xiangBean);
    }
}
