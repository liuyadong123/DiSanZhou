package com.example.dong.disanzhou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dong.disanzhou.R;
import com.example.dong.disanzhou.activity.XiangActivity;
import com.example.dong.disanzhou.adapter.ShopAdapter;
import com.example.dong.disanzhou.bean.GouBean;
import com.example.dong.disanzhou.bean.ShopBean;
import com.example.dong.disanzhou.bean.XiangBean;
import com.example.dong.disanzhou.contract.GouContrct;
import com.example.dong.disanzhou.presenter.GouPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

public class WodeFragment extends Fragment implements GouContrct.IView,XRecyclerView.LoadingListener
,ShopAdapter.OnitemCallback
{
    private ShopAdapter shopAdapter;
    private GouPresenter gouPresenter;
    private XRecyclerView rv;
    private TextView  ed,tv;
    private int page = 1;
    private String count = "5";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.wode_fragment,container,false);
         initView(view);
         initData();
        return view;
    }

    private void initData() {
        gouPresenter =new GouPresenter(this);
    }

    private void initView(View view) {
        rv=view.findViewById(R.id.rv);
        ed=view.findViewById(R.id.ed);
        tv=view.findViewById(R.id.tv);
        rv.setLoadingListener(this);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv.setLoadingListener(this);
        rv.setLoadingMoreEnabled(true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ititDatas();
            }
        });
    }

    private void ititDatas() {
        String s = ed.getText().toString();
        HashMap<String, String> params = new HashMap<>();
        params.put("keyword", s);
        params.put("page", page + "");
        params.put("count", count);
        gouPresenter.shop(new HashMap<String, String>(),params);
    }

    @Override
    public void ShopSuccess(ShopBean shopBean) {
        if (page==1) {
            rv.refreshComplete();
            shopAdapter = new ShopAdapter(getActivity());
            rv.setAdapter(shopAdapter);
            shopAdapter.setCallback(this);
            shopAdapter.setList(shopBean.result);

        } else {
            if (shopAdapter==null){
                shopAdapter = new ShopAdapter(getActivity());
                rv.setAdapter(shopAdapter);
                shopAdapter.setList(shopBean.result);
                shopAdapter.setCallback(this);
            }else {
                shopAdapter.addall(shopBean.result);
            }
            rv.loadMoreComplete();
        }
    }

    @Override
    public void XiangSuccess(XiangBean xiangBean) {
        EventBus.getDefault().postSticky(xiangBean);
        startActivity(new Intent(getActivity(),XiangActivity.class));
    }

    @Override
    public void gouSuccess(GouBean gouBean) {

    }

    @Override
    public void Failure(String msg) {

    }


    @Override
    public void onRefresh() {
         page=1;
        ititDatas();

    }

    @Override
    public void onLoadMore() {
        page++;
        ititDatas();

    }

    @Override
    public void ccback(String id) {
     gouPresenter.xiang(new HashMap<String, String>(),id);
    }
}
