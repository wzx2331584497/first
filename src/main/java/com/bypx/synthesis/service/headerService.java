package com.bypx.synthesis.service;

import com.bypx.synthesis.bean.ResInfo;
import com.bypx.synthesis.bean.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class headerService {
@Resource
com.bypx.synthesis.dao.User2Interface User2Interface;
    public ResInfo avater(User user){
        User query = User2Interface.query(user);
        return ResInfo.succes(query);
    }
}
