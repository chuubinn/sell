package com.imooc.sell.dataobject.enums;

import lombok.Getter;

@Getter
public enum OrderMasterEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),
    ;

    private  Integer code;

    private String message;

    OrderMasterEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
