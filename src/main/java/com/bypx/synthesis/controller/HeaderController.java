package com.bypx.synthesis.controller;

import com.bypx.synthesis.bean.ResInfo;
import com.bypx.synthesis.bean.User;
import com.bypx.synthesis.service.headerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("header")
public class HeaderController {
@Resource
com.bypx.synthesis.service.headerService headerService;
    @RequestMapping("avater")
public ResInfo avater(User user){
    try{
        return   headerService.avater(user);
    }catch (Exception e){
        return  ResInfo.error(e.getMessage());
    }
}
    @RequestMapping("HeadPhoto")
    public ResInfo HeadPhoto(User user){
        try{

            return   headerService.avater(user);
        }catch (Exception e){
            return  ResInfo.error(e.getMessage());
        }
    }
}
