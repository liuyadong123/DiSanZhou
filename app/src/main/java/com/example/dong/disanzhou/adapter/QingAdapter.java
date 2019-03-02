package com.example.dong.disanzhou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dong.disanzhou.R;
import com.example.dong.disanzhou.bean.XiangBean;

public class QingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private XiangBean.ResultBean xiangBean;
    private Context context;

    public QingAdapter(XiangBean.ResultBean xiangBean, Context context) {
        this.xiangBean = xiangBean;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i)==0){
            View view =LayoutInflater.from(context).inflate(R.layout.qing_view,viewGroup,false);
            QingVH qingVH =new QingVH(view);
            return qingVH;
        }else if(getItemViewType(i)==1){
            View view =LayoutInflater.from(context).inflate(R.layout.name_view,viewGroup,false);
            NameVH nameVH =new NameVH(view);
            return  nameVH;
        }else {
            View view =LayoutInflater.from(context).inflate(R.layout.webview_item,viewGroup,false);
            WebVH webVH =new WebVH(view);
            return webVH;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
         if (getItemViewType(i)==0){
             String[] split = xiangBean.picture.split(",");
             ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(split, context);
             ((QingVH)holder).page.setAdapter(viewPagerAdapter);
         }else  if (getItemViewType(i)==1){
             ((NameVH)holder).name2.setText(xiangBean.categoryName);
             ((NameVH)holder).price2.setText("￥:"+xiangBean.price);
             ((NameVH)holder).yishou.setText(xiangBean.commentNum);

         }else {
             ((WebVH)holder).webView.setWebChromeClient(new WebChromeClient());
             ((WebVH)holder).webView.loadDataWithBaseURL(null,xiangBean.details,"text/html","utf-8",null);
             ((WebVH)holder).mai.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     tianjiaCallback.maicallback();
                 }
             });
             ((WebVH)holder).gou.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     tianjiaCallback.goucallback();
                 }
             });
         }
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
           return 0;
        }else if (position==1){
            return 1;
        }else {
            return 2;
        }
    }

    public class QingVH extends RecyclerView.ViewHolder {
        private ViewPager page;
        public QingVH(@NonNull View itemView) {
            super(itemView);
            page=itemView.findViewById(R.id.page);
        }
    }
    public class NameVH extends RecyclerView.ViewHolder {
        private TextView price2,yishou,name2;
        public NameVH(@NonNull View itemView) {
            super(itemView);
            price2=itemView.findViewById(R.id.price2);
            yishou=itemView.findViewById(R.id.yishou);
            name2=itemView.findViewById(R.id.name2);
        }
    }
    public class WebVH extends RecyclerView.ViewHolder {
        private WebView webView;
        private ImageView gou,mai;
        public WebVH(@NonNull View itemView) {
            super(itemView);
        webView=itemView.findViewById(R.id.webView12);
        gou=itemView.findViewById(R.id.gouwuche);
        mai=itemView.findViewById(R.id.mai);
        }
    }
   public   TianjiaCallback tianjiaCallback;

    public void setTianjiaCallback(TianjiaCallback tianjiaCallback) {
        this.tianjiaCallback = tianjiaCallback;
    }

    public interface  TianjiaCallback{
         void goucallback();
         void maicallback();
     }
}
