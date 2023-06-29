package com.bypx.synthesis.controller;

import com.bypx.synthesis.bean.ResInfo;
import com.bypx.synthesis.bean.inputparam;
import com.bypx.synthesis.bean.jxcproductorder;
import com.bypx.synthesis.service.StockInService;
import com.bypx.synthesis.service.StockOutService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//入库单
@RestController
@RequestMapping("StockIn")
public class StockInController {
@Resource
    StockInService StockInService;
//查询订单
    @RequestMapping("query")
    public ResInfo query(jxcproductorder jxcproductorder, inputparam inputparam){
        try{

            return      StockInService.query(jxcproductorder,inputparam);


        }catch (Exception e){
            return ResInfo.error(e.getMessage());
        }
    }
//删除订单
    @RequestMapping("dele")
    public ResInfo dele(String id){
        try{
            StockInService.dele(id);
            return   ResInfo.succes(200);
        }catch (Exception e){
            return ResInfo.error(e.getMessage());
        }
    }
    //修改订单
    @RequestMapping("upload")
    public ResInfo upload(jxcproductorder jxcproductorder){
        try{
            StockInService.upload(jxcproductorder);
            return   ResInfo.succes(200);
        }catch (Exception e){
            return ResInfo.error(e.getMessage());
        }
    }
}
