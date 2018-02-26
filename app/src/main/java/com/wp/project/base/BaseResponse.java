package com.wp.project.base;


import java.io.Serializable;

/**
 * Created by 王攀 on 2017/2/22.
 */

public class BaseResponse<T> implements Serializable {

    private int result;
    private String msg;
    private String sign;
    private T jsonresult;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "result=" + result +
                ", msg='" + msg + '\'' +
                ", sign='" + sign + '\'' +
                ", jsonresult=" + jsonresult +
                '}';
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public T getJsonresult() {
        return jsonresult;
    }

    public void setJsonresult(T jsonresult) {
        this.jsonresult = jsonresult;
    }
}
