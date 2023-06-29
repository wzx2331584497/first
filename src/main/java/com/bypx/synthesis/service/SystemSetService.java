package com.bypx.synthesis.service;

import com.bypx.synthesis.dao.RoleInterface;
import com.bypx.synthesis.dao.MenuroleInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class SystemSetService {
@Resource
    SystemSetService SystemSetService;
    @Resource
    com.bypx.synthesis.dao.textInterface textInterface;
    @Resource
    RoleInterface RoleInterface;
    @Resource
    MenuroleInterface menuroleInterface;


}
