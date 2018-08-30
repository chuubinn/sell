package com.imooc.sell.controller;

import com.imooc.sell.dataobject.enums.ResultEnum;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public  ModelAndView create(@RequestParam("orderId") String orderId,
                                @RequestParam("returnUrl") String returnurl){
        //1.查询订单
        OrderDTO orderDTO = orderService.findById(orderId);
        if (orderDTO == null){
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.发起支付
        return new ModelAndView("pay/create");

    }

}
