package com.shenzhu.android.happyweather.activity.util;


public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
