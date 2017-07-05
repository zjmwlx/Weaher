package com.zjm.weaher.util;

import android.text.TextUtils;

import com.zjm.weaher.db.City;
import com.zjm.weaher.db.County;
import com.zjm.weaher.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by zjm on 2017/7/5.解析省，市，县三级数据的json字符串
 */

public class Utility {
    public static boolean handlerProvinceResponse (String response){
        //判断传入的json字符串response是否为空，如果不为空，就解析传入的字符串
        if(TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(jsonObject.getString("name"));
                    province.setProvinceCode(jsonObject.getInt("id"));
                    province.save();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;

        }else {
            return false;
        }

    }
    public static boolean handlerCityResponse(String response,int provinceId){
        if (TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setCityName(jsonObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }else {
            return false;
        }

    }

public static boolean handlerCountyResponse(String responce,int ciyId){
    if (TextUtils.isEmpty(responce)){
        try {
            JSONArray jsonArray = new JSONArray(responce);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjecet = jsonArray.getJSONObject(i);
                County county = new County();
                county.setCountyCode(jsonObjecet.getInt("id"));
                county.setCountyName(jsonObjecet.getString("name"));
                county.setCityId(ciyId);
                county.save();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }else {
        return false;
    }

}










}
