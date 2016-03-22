package com.shenzhu.android.happyweather.activity.util;

import android.text.TextUtils;

import com.shenzhu.android.happyweather.activity.db.HappyWeatherDB;
import com.shenzhu.android.happyweather.activity.model.City;
import com.shenzhu.android.happyweather.activity.model.County;
import com.shenzhu.android.happyweather.activity.model.Province;

public class Utility {
    /**
     * Parse and handle province data
     */
    public synchronized static boolean handleProvincesResponse(HappyWeatherDB happyWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //save to database
                    happyWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Parse and handle city data
     */
    public synchronized static boolean handleCitiesResponse(HappyWeatherDB happyWeatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //save to database
                    happyWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Parse and handle couty data
     */
    public synchronized static boolean handleCountiesResponse(HappyWeatherDB happyWeatherDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //save to database
                    happyWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
