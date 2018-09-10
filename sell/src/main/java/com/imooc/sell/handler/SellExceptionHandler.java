package com.imooc.sell.handler;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.exception.ResponseBankException;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.exception.SellerAuthorizeException;
import com.imooc.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Cacheable;
import javax.validation.Valid;

/**
 * 捕获抛出的登陆授权异常
 */
@ControllerAdvice
public class SellExceptionHandler {


    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登陆异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException(){
       return new ModelAndView("redirect:"
       .concat(projectUrlConfig.getWechatOpenAuthorize())
       .concat("/sell/seller/qrAuthorize")
       .concat("?returnUrl=")
       .concat(projectUrlConfig.getSell())
       .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.errorr(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value= ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerResponseBankException(){

    }
}
