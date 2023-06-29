package com.bypx.synthesis.controller;

import com.bypx.synthesis.bean.ResInfo;
import com.bypx.synthesis.bean.inputparam;
import com.bypx.synthesis.bean.jxcproductorder;
import com.bypx.synthesis.service.StockOutService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//出库单
@RestController
@RequestMapping("StockOut")
public class StockOutController {
@Resource
    StockOutService  StockOutService;

//数据查询
 @RequestMapping("query")
    public ResInfo query(inputparam inputparam,jxcproductorder jxcproductorder ){
        try{

        return StockOutService.query(inputparam,jxcproductorder);

        }catch (Exception e){
            return ResInfo.error(e.getMessage());
        }
    }
    //删除订单
    @RequestMapping("dele")
    public ResInfo dele(String id){
        try{
          StockOutService.dele(id);
            return   ResInfo.succes(200);
        }catch (Exception e){
            return ResInfo.error(e.getMessage());
        }
    }
    //修改订单
    @RequestMapping("upload")
    public ResInfo upload(jxcproductorder jxcproductorder){
        try{
            StockOutService.upload(jxcproductorder);
            return   ResInfo.succes(200);
        }catch (Exception e){
            return ResInfo.error(e.getMessage());
        }
    }

    //订单库存判断是否足够
    //传入订单名字typeid
    @RequestMapping("judge")
    public ResInfo judge (jxcproductorder jxcproductorder){
        try{
            return      StockOutService.judge(jxcproductorder);

        }catch (Exception e){
            return ResInfo.error(e.getMessage());
        }
    }

}
