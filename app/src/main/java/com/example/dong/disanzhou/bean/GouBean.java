package com.example.dong.disanzhou.bean;

import java.util.List;

public class GouBean {
    public String message;
    public String status;
    public List<Result> result;
    public  class  Result{
        public String commodityId;
        public String commodityName;
        public String count;
        public String pic;
        public double price;
        public int commodityNum;
        public boolean commoditycheckbox;
    }
}
