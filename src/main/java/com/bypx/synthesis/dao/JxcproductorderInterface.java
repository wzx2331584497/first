package com.bypx.synthesis.dao;

import com.bypx.synthesis.bean.jxcproductorder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JxcproductorderInterface {
    public List<jxcproductorder> queryall(int a);
    public void add_reduce(jxcproductorder data);
    public List<jxcproductorder>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                queryStoreout(jxcproductorder jxcproductorder);
    public List<jxcproductorder> queryStorein(jxcproductorder jxcproductorder);
    public List<jxcproductorder> queryDocumentaudit(jxcproductorder jxcproductorder);
    public void dele( String id);
    public void upload( jxcproductorder jxcproductorder);
    public void pass( jxcproductorder jxcproductorder);
    public void reject( jxcproductorder jxcproductorder);
}
