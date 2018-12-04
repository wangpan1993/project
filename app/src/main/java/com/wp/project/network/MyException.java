package com.wp.project.network;

import com.google.gson.Gson;
import com.wp.project.modle.BaseResponseBean;

public class MyException extends IllegalStateException {

    private BaseResponseBean errorBean;

    public MyException(String s) {
        super(s);
        errorBean = new Gson().fromJson(s, BaseResponseBean.class);
    }

    public BaseResponseBean getErrorBean() {
        return errorBean;
    }
}
