package com.wp.project.network;

import com.wp.project.base.BaseResponse;

/**
 * Created by 王攀 on 2017/2/22.
 * 异常类
 */

public class ApiException extends RuntimeException {
    private BaseResponse<String> errorResponse;

    public ApiException(BaseResponse<String> errorResponse) {
        super(errorResponse.getReason());
        this.errorResponse = errorResponse;
        errorDeal(errorResponse);
    }

    private void errorDeal(BaseResponse<String> errorResponse) {
        switch (errorResponse.getError_code()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                break;
        }

    }
}
