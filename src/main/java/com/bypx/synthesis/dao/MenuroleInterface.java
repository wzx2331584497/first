package com.bypx.synthesis.dao;

import com.bypx.synthesis.bean.menu;
import com.bypx.synthesis.bean.menurole;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuroleInterface {
    public void add(menurole menurole);
    public void dele(String role);


}
