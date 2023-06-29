package com.bypx.synthesis.service;

import com.bypx.synthesis.bean.Log;
import com.bypx.synthesis.dao.Logdaointerface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/* @author:wzx
 @Date:  2023/4/28
*/
//用于写入日志数据到mysql
@Service
@Transactional
public class LogdaoService {
    //调用dao接口 实现抽象方法
    @Resource
    Logdaointerface Log;

    public void add(Log log){
        Log.logdao(log);
    }


}
