package com.bypx.synthesis.dao;

import com.bypx.synthesis.bean.jxcproduct;
import com.bypx.synthesis.bean.jxcproductorder;
import org.springframework.stereotype.Repository;

import java.util.List;
//23hj //232323
@Repository
public interface JxcproductInterface {
    //23hj
    public List<jxcproduct> queryall(jxcproduct data);
    public void upload(jxcproduct data);
    public jxcproduct query(jxcproduct jxcproduct);
    public void add(jxcproduct data);
    public jxcproduct judge(String typeid);
    public void dele(jxcproduct jxcproduct);
    public jxcproduct queryone(jxcproduct jxcproduct);
}
