package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

/*
* 支付
*
* */
public interface PayService {

    void create(OrderDTO orderDTO);
}
