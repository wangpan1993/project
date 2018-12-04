package com.wp.project.modle;

import java.io.Serializable;
public class BaseResponseBean implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int Code;
    public String Msg;

    public ResponseBean toResponseBean() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.error_code = Code;
        responseBean.reason = Msg;
        return responseBean;
    }
}
