package com.imooc.sell.controller;

import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.dataobject.enums.ResultEnum;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.VO.ResultVO;
import com.imooc.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bingdingResult) {
      if (bingdingResult.hasErrors()){
          log.error("【创建订单】 参数不正确,orderForm={}",orderForm);
          throw new SellException(bingdingResult.getFieldError().getDefaultMessage(),
                  ResultEnum.PARAM_ERROR.getCode());
      }
      OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
      if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
          log.error("【创建订单】 购物车不能为空");
          throw new SellException(ResultEnum.CART_EMPTY);
      }
      OrderDTO createResult = orderService.create(orderDTO);
      Map<String,String> map = new HashMap<>();
      map.put("orderId",createResult.getOrderId());

      return ResultVOUtil.success(map);
    }
    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);
        return  ResultVOUtil.success(orderDTOPage.getContent());

    }
    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
        /*
        //TODO 不安全的做法，改进,因为只要知道orderId,就可以查询所有买家信息
        OrderDTO orderDTO = orderService.findById(orderId);
        return ResultVOUtil.success(orderDTO);
        */

        //改进后的做法
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDTO);
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
        /*
        //TODO 不安全的做法，改进，因为只要知道orderId,就可以查询所有买家信息
        OrderDTO orderDTO = orderService.findById(orderId);
        orderService.cancel(orderDTO);
        return ResultVOUtil.success();
        */
        buyerService.cancelOrder(openid,orderId);
        return ResultVOUtil.success();
    }
}
