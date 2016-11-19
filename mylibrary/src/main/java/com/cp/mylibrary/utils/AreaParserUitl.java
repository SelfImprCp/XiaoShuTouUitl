package com.cp.mylibrary.utils;

import android.content.Context;

import com.cp.mylibrary.bean.ComBean;
import com.cp.mylibrary.bean.XSTAreaBean;
import com.cp.mylibrary.city.CityPicker;
import com.cp.mylibrary.city.Cityinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 *  暂时用的把省市区转化成指定的模式
 * Created by Jerry on 2016/11/18.
 */

public class AreaParserUitl {

    private List<Cityinfo> province_list = new ArrayList<Cityinfo>();
    private HashMap<String, List<Cityinfo>> city_map = new HashMap<String, List<Cityinfo>>();
    private HashMap<String, List<Cityinfo>> couny_map = new HashMap<String, List<Cityinfo>>();

     public  void openGson(Context context)
     {
//         // 读取城市信息string
////        JSONParser parser = new JSONParser();
//
//         String strAreaJson = GsonUtil.getJsonForFile(context, "xst_area.txt");
////
////		LogCp.i(LogCp.CP,CityPicker.class + "解析出来了多少数据 strJson  " + strAreaJson);
//
//         XSTAreaBean areaInfos = GsonUtil.jsonStrToBean(strAreaJson, XSTAreaBean.class);
//
//
//         LogCp.i(LogCp.CP, CityPicker.class + "解析出来了多少数据 " + areaInfos.getAreaInfos().size());
//
//// 整理省
//         for (Cityinfo xstAreaNameBean : areaInfos.getAreaInfos()) {
//             // 没有父id 是省
//             if (xstAreaNameBean.getParentid() == 0) {
//
//                 province_list.add(xstAreaNameBean);
//             }
//
//         }
//
//         // 整理市
//         List<Cityinfo> cityinfoList = new ArrayList<>();
//
//         for (Cityinfo cityinfo : province_list) {
//             // 省名
//             String proName = cityinfo.getAreaname();
//
//             // 所有的数据
//             for (Cityinfo xstAreaNameBean : areaInfos.getAreaInfos()) {
//
//                 //int currentID = xstAreaNameBean.getParentid();
//                 // 如果当前的父id 等于省的id ，属于这个省
//                 LogCp.i(LogCp.CP, CityPicker.class + " 当前区的市 父id  " +  xstAreaNameBean.getParentid() + " 父亲的正ID " + cityinfo.getId());
//
//
//                 if (xstAreaNameBean.getParentid() == cityinfo.getId()) {
//
//
//
//                     cityinfoList.add(xstAreaNameBean);
//
//                 }
//
//             }
//
//             city_map.put(proName, cityinfoList);
//
//
//         }
//
//         // 整理区
//
//         List<Cityinfo> countyList = new ArrayList<>();
//
//
//         for (String key : city_map.keySet()) {
//             List<Cityinfo> cityList = city_map.get(key);
//
//
//             // 所有的数据
//             for (Cityinfo xstAreaNameBean : cityList) {
//
//                 for (Cityinfo county : areaInfos.getAreaInfos()) {
//                     int currentID = county.getParentid();
//                     // 如果当前的父id 等于省的id ，属于这个省
//                     if (currentID == xstAreaNameBean.getId()) {
//                         countyList.add(xstAreaNameBean);
//
//                     }
//                 }
//
//
//             }
//
//             couny_map.put(key, cityList);
//
//         }
//
//
//
//         ComBean comBean = new ComBean();
//         comBean.setProvince_list(province_list);
//         comBean.setCity_map(city_map);
//         comBean.setCouny_map(couny_map);
//
//          String gsonStr = GsonUtil.beanTojsonStr(comBean);
//
//
//
//
//
//         LogCp.i(LogCp.CP, CityPicker.class + "解析出来了多少数据 多少省 " + province_list.size()  +"多少市 " + city_map.size() + " 多少区" + couny_map.size());
//
//
//         LogCp.i(LogCp.CP, CityPicker.class + " 完了后的gson  " +   gsonStr);
//
//
//
//         FileUtil.saveContentToSDCard(gsonStr,SDCardUtils.getSDCardPath()+"/MyCp/","xstarea.json");
//
//
//

     }





}
