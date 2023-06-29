package com.bypx.synthesis.controller;

import com.bypx.synthesis.bean.*;
import com.bypx.synthesis.service.StoreService;
import org.apache.catalina.Store;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.visitor.Reifier;

import javax.annotation.Resource;

//库存汇总
@RestController
@RequestMapping("Store")
public class StoreController {
@Resource
    StoreService  StoreService;
//Store表格数据查询
    @RequestMapping("queryall")
    public ResInfo queryall(jxcproduct data, inputparam inputparam){
        try{
           return   StoreService.queryall(data,inputparam);
        }catch (Exception e){
            return  ResInfo.error(e.getMessage());
        }
    }
// tree数据
    @RequestMapping("querytree")
    public ResInfo MaterialManagement(Material Material) {
        try{
            return StoreService.MaterialManagement(Material);
        }catch (Exception e){
            return  ResInfo.error(e.getMessage());
        }

    }
//采购入库
    @RequestMapping("procurementAndStorage")
    public ResInfo procurementAndStorage(jxcproductorder data,String account) {
        try{
            return StoreService.procurementAndStorage(data,account);
        }catch (Exception e){
            return  ResInfo.error(e.getMessage());
        }
    }
    //销售出库
    @RequestMapping("SaleOutOfStorage")
    public  ResInfo SaleOutOfStorage(jxcproductorder data, String account){
        try{
            return StoreService.SaleOutOfStorage(data,account);
        }catch (Exception e){
            return ResInfo.succes(e.getMessage());
        }
    }
    @RequestMapping("SaleInOfStorage")
    public  ResInfo SaleInOfStorage(jxcproductorder data, String account){
        try{
            return StoreService.SaleOutOfStorage(data,account);
        }catch (Exception e){
            return ResInfo.succes(e.getMessage());
        }
    }

    @RequestMapping("uploadtotal")
    public ResInfo queryproduct(jxcproduct jxcproduct){
        ResInfo queryproduct = StoreService.queryproduct(jxcproduct);
        return queryproduct;
    }



}

