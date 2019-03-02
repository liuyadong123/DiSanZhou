package com.example.dong.disanzhou.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dong.disanzhou.R;
import com.example.dong.disanzhou.bean.ShopBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopVH> {
  private List<ShopBean.shopData> list;
  private Context context;

    public ShopAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public  void  setList(List<ShopBean.shopData> list){
        this.list=list;
        notifyDataSetChanged();
    }
    public  void  addall(List<ShopBean.shopData> list){
        if (list!=null) {
           this.list.addAll(list);
            notifyDataSetChanged();
        }


 }
    @NonNull
    @Override
    public ShopAdapter.ShopVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.shop_item,parent,false);
        ShopVH shopVH =new ShopVH(view);
        return shopVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ShopVH holder, final int position) {
        Uri uri =Uri.parse(list.get(position).masterPic);

        DraweeController draweeController =Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.image.setController(draweeController);
        holder.price.setText(list.get(position).price);
        holder.name.setText(list.get(position).commodityName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.ccback(list.get(position).commodityId);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ShopVH extends RecyclerView.ViewHolder {
        private SimpleDraweeView image;
        private TextView name,price,bzd;
        public ShopVH(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            bzd=itemView.findViewById(R.id.bzd);
        }
    }
    private  OnitemCallback callback;

    public void setCallback(OnitemCallback callback) {
        this.callback = callback;
    }

    public interface  OnitemCallback{
        void ccback(String id);

    }
}
