package com.example.dong.disanzhou.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dong.disanzhou.R;
import com.example.dong.disanzhou.bean.GouBean;
import com.example.dong.disanzhou.net.PriceCallaback;
import com.example.dong.disanzhou.view.MyView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class GouwuAdapter extends RecyclerView.Adapter<GouwuAdapter.GouVH> {
    private List<GouBean.Result> list;
    private Context context;
    private PriceCallaback priceCallaback;

    public void setPriceCallaback(PriceCallaback priceCallaback) {
        this.priceCallaback = priceCallaback;
    }

    public GouwuAdapter(List<GouBean.Result> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public GouVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.gou_item,parent,false);
        GouVH gouVH =new GouVH(view);
        return gouVH;
    }

    @Override
    public void onBindViewHolder(@NonNull GouVH holder, final int position) {
        Uri uri =Uri.parse(list.get(position).pic);
        DraweeController draweeController=Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setUri(uri)
                .build();
        holder.gouimage.setController(draweeController);

        holder.gouprice.setText(list.get(position).price+"");

        holder.gouname.setText(list.get(position).commodityName);

        holder.gouck.setChecked(list.get(position).commoditycheckbox);

        holder.myView.setAddJIanCallback(new MyView.AddJIanCallback() {
            @Override
            public void AddCallback(int name) {
                list.get(position).commodityNum=name;
                priceCallaback.pricecall();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class GouVH extends RecyclerView.ViewHolder {
        private CheckBox gouck;
        private TextView gouname,gouprice;
        private SimpleDraweeView gouimage;
        private MyView myView;
        public GouVH(View itemView) {
            super(itemView);
            gouck=itemView.findViewById(R.id.gouck);
            gouname=itemView.findViewById(R.id.goutext);
            gouprice=itemView.findViewById(R.id.gouprice);
            gouimage=itemView.findViewById(R.id.gouimage);
            myView=itemView.findViewById(R.id.myview);
        }
    }
}
