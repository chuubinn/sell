package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum OrderMasterEnum implements CodeEnum{
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

/**
 * 这样写代码，要在每一个枚举下都要重复写这么个代码，可以先写一个枚举的接口类，然后再写一个EnumUtil工具类，这样可以避免写重复的代码、
 *
 *
 * public static OrderMasterEnum getOrderMasterEnum(Integer code){
        for (OrderMasterEnum orderMasterEnum: OrderMasterEnum.values()){
            if (orderMasterEnum.getCode().equals(code)){
                return orderMasterEnum;
            }
        }
        return null;
    }
 */
}
