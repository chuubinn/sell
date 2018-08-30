package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/*
* 支付
*
* */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String noifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
