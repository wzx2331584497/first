package com.bypx.synthesis.dao;

import com.bypx.synthesis.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface User2Interface {
    public User queryaccountid(String account);
    public User query(User user);
}
