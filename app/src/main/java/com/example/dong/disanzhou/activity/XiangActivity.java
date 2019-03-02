package com.example.dong.disanzhou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dong.disanzhou.R;
import com.example.dong.disanzhou.adapter.QingAdapter;
import com.example.dong.disanzhou.bean.GouBean;
import com.example.dong.disanzhou.bean.ShopBean;
import com.example.dong.disanzhou.bean.XiangBean;
import com.example.dong.disanzhou.contract.GouContrct;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class XiangActivity extends AppCompatActivity implements GouContrct.IView, QingAdapter.TianjiaCallback {
    private RecyclerView qingrv;
    private XiangBean.ResultBean result;
    private QingAdapter qingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        qingrv=findViewById(R.id.qing);
        qingAdapter = new QingAdapter(result,this);
        qingrv.setAdapter(qingAdapter);
        qingrv.setLayoutManager(new LinearLayoutManager(this));
        qingAdapter.setTianjiaCallback(this);
    }
    //EventBus穿过来的值
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public  void shopwewe(XiangBean xiangBean){
        result = xiangBean.result;

    }
    @Override
    public void XiangSuccess(XiangBean xiangBean) {

    }
    @Override
    public void gouSuccess(GouBean gouBean) {

    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void ShopSuccess(ShopBean shopBean) {

    }


    @Override
    public void goucallback() {

    }

    @Override
    public void maicallback() {

    }
}
