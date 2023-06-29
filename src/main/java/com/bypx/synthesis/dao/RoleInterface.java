package com.bypx.synthesis.dao;


import com.bypx.synthesis.bean.role;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleInterface {
    public void add(role role);
    public void dele(String name);
    public List<role> rolequery(role role);
    public void set(role name);


}
