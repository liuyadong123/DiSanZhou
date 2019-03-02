package com.example.dong.disanzhou.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class ViewPagerAdapter extends android.support.v4.view.PagerAdapter {
    private String[] image1;
    private Context context;

    public ViewPagerAdapter(String[] image, Context context) {
        this.image1 = image;
        this.context = context;
    }

    @Override
    public int getCount() {
        return image1.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SimpleDraweeView simpleDraweeView =new SimpleDraweeView(context);
        Uri uri =Uri.parse(image1[position]);

        DraweeController draweeController =Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(20);
        simpleDraweeView.setController(draweeController);
        simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
        container.addView(simpleDraweeView);
        return simpleDraweeView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
