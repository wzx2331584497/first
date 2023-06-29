package com.bypx.synthesis.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.bypx.synthesis.bean.ResInfo;
import com.bypx.synthesis.bean.VerifyCode;
import com.bypx.synthesis.bean.jxcproduct;
import com.bypx.synthesis.config.redis.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.awt.image.BufferedImage;
import java.io.IOException;
@RestController
@RequestMapping("login")
public class loginController {
    @Resource
    com.bypx.synthesis.service.loginService loginService;
    @Resource
    RedisUtil redisUtil;

    //更新物品总数
    @RequestMapping("stroetotal")
    public ResInfo queryproduct(jxcproduct jxcproduct){
        ResInfo queryproduct = loginService.queryproduct(jxcproduct);
        return queryproduct;
    }
    //实现短信平台发送验证码并且缓存在redis
    @RequestMapping("phonecode")
    public ResInfo telephotocode(String phone,HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //创建验证码生成器实例取得生成图片和随机字符串
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        System.out.println(text);
        //用流传输

//redisUtil.set("imgcode",text);
//        String imgcode = (String)redisUtil.get("imgcode");
//        System.out.println("读取的验证码"+imgcode);
        HttpSession a=req.getSession();
a.setAttribute("session",text);
        String session = (String)a.getAttribute("session");
        System.out.println("session中的imgcode"+session);
        VerifyCode.output(image, resp.getOutputStream());
        return ResInfo.succes(200);

    }




}
