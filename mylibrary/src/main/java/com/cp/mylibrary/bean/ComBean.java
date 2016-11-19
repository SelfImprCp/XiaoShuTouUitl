package com.cp.mylibrary.bean;

import com.cp.mylibrary.city.Cityinfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jerry on 2016/11/18.
 */

public class ComBean {



     private List<Cityinfo> province_list;

    private HashMap<String, List<Cityinfo>> city_map;


    private HashMap<String, List<Cityinfo>> couny_map;


    public List<Cityinfo> getProvince_list() {
        return province_list;
    }

    public void setProvince_list(List<Cityinfo> province_list) {
        this.province_list = province_list;
    }

    public HashMap<String, List<Cityinfo>> getCity_map() {
        return city_map;
    }

    public void setCity_map(HashMap<String, List<Cityinfo>> city_map) {
        this.city_map = city_map;
    }

    public HashMap<String, List<Cityinfo>> getCouny_map() {
        return couny_map;
    }

    public void setCouny_map(HashMap<String, List<Cityinfo>> couny_map) {
        this.couny_map = couny_map;
    }
}
