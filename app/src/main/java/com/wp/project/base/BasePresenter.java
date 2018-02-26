package com.wp.project.base;


import android.content.Context;
import android.os.Message;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王攀 on 2017/2/22.
 */

public class BasePresenter{

    protected BaseView mView;
    protected Message mMessage;
    protected Context mContext;

    public BaseView getView() {
        return mView;
    }

    public BasePresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void attachView(BaseView t) {
        this.mView = t;
        mMessage = new Message();
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
        if (mMessage != null) {
            mMessage = null;
        }
    }

    //统一请求参数处理
    protected Map<String, String> getParams(String[] keys, Object[] values) {
        Map<String, Object> map = new HashMap<>();
        int keyLength = keys == null ? 0 : keys.length;
        int valuesLength = values == null ? 0 : values.length;
        if (keyLength != valuesLength) {
            throw new IllegalArgumentException("参数长度不对等");
        }
        for (int i = 0; i < keyLength; i++) {
            map.put(keys[i], values[i]);
        }

        map.put("jsondata中的公共参数","jsondata中的公共参数");
        map.put("jsondata中的公共参数","jsondata中的公共参数");
        map.put("jsondata中的公共参数","jsondata中的公共参数");
        map.put("jsondata中的公共参数","jsondata中的公共参数");
        map.put("jsondata中的公共参数","jsondata中的公共参数");
        map.put("jsondata中的公共参数","jsondata中的公共参数");
        map.put("jsondata中的公共参数","jsondata中的公共参数");
        ObjParamsToStringParams("","",map);
        return ObjParamsToStringParams("","",map);
    }

    //统一请求参数处理
    protected Map<String, String> ObjParamsToStringParams(String bizid, String marking, Map<String, Object> objectMap) {
        Map<String, String> map = new HashMap<>();
        map.put("bizid", bizid);
        map.put("marking", marking);
        Gson gson = new Gson();
        String jsondata = gson.toJson(objectMap);
        map.put("jsondata", jsondata);

        return map;
    }

}
