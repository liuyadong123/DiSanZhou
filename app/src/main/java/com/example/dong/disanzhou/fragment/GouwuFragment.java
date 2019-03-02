package com.example.dong.disanzhou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.dong.disanzhou.R;
import com.example.dong.disanzhou.activity.JiesuanActivity;
import com.example.dong.disanzhou.adapter.GouwuAdapter;
import com.example.dong.disanzhou.bean.GouBean;
import com.example.dong.disanzhou.bean.ShopBean;
import com.example.dong.disanzhou.bean.XiangBean;
import com.example.dong.disanzhou.contract.GouContrct;
import com.example.dong.disanzhou.net.PriceCallaback;
import com.example.dong.disanzhou.presenter.GouPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GouwuFragment extends Fragment implements GouContrct.IView,PriceCallaback {
    private GouwuAdapter gouwuAdapter;
    private RecyclerView gouwurv;
    private CheckBox gouqx;
    private TextView heji,jiesuan;
    private GouPresenter gouPresenter;
    private String userId="216";
    private String sessionId="1551493891791216";
    private List<GouBean.Result> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.gouwu_fragment,container,false);

         initView(view);
         initData();
        return view;
    }

    private void initView(View view) {
        gouwurv=view.findViewById(R.id.gouwurv);
        gouqx=view.findViewById(R.id.gouquan);
        heji=view.findViewById(R.id.gouheji);
        jiesuan=view.findViewById(R.id.gouqujiesuan);
        gouwurv.setLayoutManager(new LinearLayoutManager(getActivity()));
        gouqx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    for (GouBean.Result result : list) {
                        result.commoditycheckbox=true;
                    }
                }else {
                    for (GouBean.Result result : list) {
                        result.commoditycheckbox=false;
                    }
                }
                getPrice();
                gouwuAdapter.notifyDataSetChanged();
            }
        });
        jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),JiesuanActivity.class));
            }
        });


    }
    public void getPrice(){
        double money=0;
        for (GouBean.Result result : list) {
            if (result.commoditycheckbox){
                money+=result.price*result.commodityNum;
            }
        }
        heji.setText("合计:"+money);

    }
    private void initData() {
        gouPresenter = new GouPresenter(this);
        HashMap<String,String> params=new HashMap<>();
        params.put("userId",userId);
        params.put("sessionId",sessionId);
        gouPresenter.gou(params,new HashMap<String, String>());
    }

    @Override
    public void gouSuccess(GouBean gouBean) {
        list=gouBean.result;
        gouwuAdapter=new GouwuAdapter(gouBean.result,getActivity());
        gouwurv.setAdapter(gouwuAdapter);
        gouwuAdapter.setPriceCallaback(this);
        for (GouBean.Result result : list) {
            result.commodityNum=1;
        }
    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void ShopSuccess(ShopBean shopBean) {

    }

    @Override
    public void XiangSuccess(XiangBean xiangBean) {

    }

    @Override
    public void pricecall() {
        getPrice();
    }
}
