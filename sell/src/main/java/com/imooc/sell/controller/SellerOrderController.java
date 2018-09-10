package com.imooc.sell.controller;
/**
 *卖家端订单
 */


import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page 第几页，从第一页开始
     * @param size 一页有多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request = PageRequest.of(page-1,size);
        Page<OrderDTO> orderDTOPage =  orderService.findList(request);
        map.put("orderDTOpage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }


    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                                Map<String, Object> map){
        try {
           OrderDTO orderDTO = orderService.findById(orderId);
           orderService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端取消订单】 发生异常{}", e);
            map.put("msg",e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("order/success");
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findById(orderId);
        }catch(SellException e){
            log.error("【卖家端查询订单详情】 发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail",map);
    }
}

