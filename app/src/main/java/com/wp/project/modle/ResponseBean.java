package com.wp.project.modle;


import java.io.Serializable;

public class ResponseBean<T> implements Serializable {

    public int error_code;
    public String reason;
    public T result;


}
