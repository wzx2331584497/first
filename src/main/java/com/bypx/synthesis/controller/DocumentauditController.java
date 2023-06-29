package com.bypx.synthesis.controller;


import com.bypx.synthesis.bean.ResInfo;
import com.bypx.synthesis.bean.inputparam;
import com.bypx.synthesis.bean.jxcproductorder;
import com.bypx.synthesis.service.DocumentauditService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
//前后端分离的spring项目
//单据审核
@RestController
@RequestMapping("Documentaudit")
public class DocumentauditController {
@Resource
    DocumentauditService  DocumentauditService;
//单据审核tabel数据
    @RequestMapping("query")
    public ResInfo query(inputparam inputparam, jxcproductorder jxcproductorder2, String type){
        try{
            return       DocumentauditService.queryDocumentaudit(inputparam, jxcproductorder2, type);
        }catch (Exception e){
            return ResInfo.error(e.getMessage());
        }
    }
    //驳回
    @RequestMapping("reject")
    public ResInfo reject( jxcproductorder jxcproductorder){
        try{
            return       DocumentauditService.reject(jxcproductorder);


        }catch (Exception e){
            return ResInfo.error(e.getMessage());
        }
    }
//通过
    @RequestMapping("pass")
    public ResInfo pass( jxcproductorder jxcproductorder){
        try{
            return  DocumentauditService.pass(jxcproductorder);

        }catch (Exception e){
            return ResInfo.error(e.getMessage());
        }


    }



}
