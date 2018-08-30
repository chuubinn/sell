package com.imooc.sell.utils;


import com.imooc.sell.service.VO.ResultVO;


public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
    public static ResultVO success(){
        return success(null);

    }
    public static ResultVO errorr(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("登陆失败");
        return resultVO;

    }

}
