package com.example.dong.disanzhou.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.disanzhou.R;


public class MyView extends LinearLayout {
    private TextView jian,jia;
    private EditText num;
    private int numss=1;
    public MyView(Context context) {
        super(context);
        initView(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        View view =LayoutInflater.from(context).inflate(R.layout.view_item,this,true);
        jian=view.findViewById(R.id.jian);
        jia=view.findViewById(R.id.jia);
        num=view.findViewById(R.id.num);
        num.setText("1");
        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                numss++;
                num.setText(numss+"");
                if (addJIanCallback!=null){
                    addJIanCallback.AddCallback(numss);
                }

            }
        });
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                numss--;
                if (numss==0){
                    numss=1;
                    Toast.makeText(context,"不能再减了",Toast.LENGTH_SHORT).show();
                }
                num.setText(numss+"");
                if (addJIanCallback!=null){
                    addJIanCallback.AddCallback(numss);
                }

            }
        });

    }
    public   AddJIanCallback addJIanCallback;

    public void setAddJIanCallback(AddJIanCallback addJIanCallback) {
        this.addJIanCallback = addJIanCallback;
    }

    public interface AddJIanCallback{
        void AddCallback(int name);

    }


}
